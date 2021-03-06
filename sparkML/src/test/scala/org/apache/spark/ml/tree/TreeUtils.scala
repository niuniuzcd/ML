package org.apache.spark.ml.tree

import org.apache.spark.ml.classification.DecisionTreeClassificationModel

import scala.collection.mutable

/**
  * Spark tree utils  for rule extraction and bins
  *
  * @author Hou Lu
  * @version 1.0, 2018/12/12
  */
object TreeUtils extends Serializable {
  /**
    * Extract path
    *
    * @param model
    */
  def extractPath(model: DecisionTreeClassificationModel) = {
    model.thresholds
  }

  /**
    * Extract con bins
    *
    * @param model
    * @return
    */
  def extractConBins(model: DecisionTreeClassificationModel): Array[Double] = {
    val splits = getSplits(model)
    val points = splits.map {
      case s: ContinuousSplit =>
        val point: Double = s.threshold
        point
      case one: Split =>
        println(s"type not constant+${one.featureIndex}")
        -100.0d
    }
    (Double.NegativeInfinity +: points.sorted :+ Double.PositiveInfinity).toArray
  }

  /**
    * Extract cate bins
    *
    * @param model
    * @return
    */
  def extractCateBins(model: DecisionTreeClassificationModel): Array[Array[Double]] = {
    val splits = getSplits(model)
    var  tempSet = Set[Double]()
    var maxIndex = 0d
    var points = mutable.ArrayBuffer[Array[Double]]()
    splits.map {
      case s: CategoricalSplit =>
        if(maxIndex < s.leftCategories.max)
          maxIndex = s.leftCategories.max
        if(tempSet.nonEmpty){
          val ts = s.leftCategories.toSet & tempSet
          if(ts.nonEmpty){
            points += (s.leftCategories.toSet -- tempSet).toArray
            tempSet =  s.leftCategories.toSet
          }
          else{
            points += s.leftCategories
            tempSet =  s.leftCategories.toSet
          }
        }else{
          tempSet =  s.leftCategories.toSet
          points += s.leftCategories
        }
      case one: Split =>
        println(s"type not constant+${one.featureIndex}")
        Array(-100.0d)
    }
    val lastBins = (0 to maxIndex.toInt).map(_.toDouble).toSet
    val finalBins =  lastBins  diff  points.flatten.toSet
    points += finalBins.toArray
    points.toArray
  }


  val init = (Double.MaxValue, null.asInstanceOf[Array[Double]], Double.MinValue)

  def extractBinInfo(model: DecisionTreeClassificationModel) = {
    val binInfos: mutable.Seq[Either[Split, Array[Double]]] = getInfo(model)
    val bins = mutable.Buffer[BinInfo]()

    var tmp: BinTmpInfo = tupleToBin(init)

    val iter = binInfos.iterator
    while (iter.hasNext) {
      // handle init
      if (tmp.top > tmp.bot) {
        val nb = tmp.top
        bins.append(BinInfo((tmp.bot, tmp.top), tmp.leaf))
        tmp = tupleToBin(init)
        tmp.bot = nb
      }

      val one = iter.next()
      one match {
        case Left(l) => {
          val sp = l match {
            case s: ContinuousSplit => {
              val point: Double = s.threshold
              point
            }
          }
          if (tmp.bot == Double.MaxValue) {
            tmp.bot = sp
          } else if (tmp.top == Double.MinValue) {
            tmp.top = sp
          }

        }
        case Right(r) => {
          tmp.leaf = r
          if (tmp.bot == Double.MaxValue) {
            tmp.bot = Double.MinValue
          }
        }
      }
    }

    if (tmp.leaf != null) {
      if (tmp.top == Double.MinValue) {
        tmp.top = Double.MaxValue
      }
      bins.append(BinInfo((tmp.bot, tmp.top), tmp.leaf))
    }
    bins
  }

  def extractCateBinInfo(model: DecisionTreeClassificationModel) = {
    val binInfos: mutable.Seq[Either[Split, Array[Double]]] = getInfo(model)
    val bins = mutable.Buffer[BinInfo]()

    var tmp: BinTmpInfo = tupleToBin(init)

    val iter = binInfos.iterator
    while (iter.hasNext) {
      // handle init
      if (tmp.top > tmp.bot) {
        val nb = tmp.top
        bins.append(BinInfo((tmp.bot, tmp.top), tmp.leaf))
        tmp = tupleToBin(init)
        tmp.bot = nb
      }

      val one = iter.next()
      one match {
        case Left(l) => {
          val sp = l match {
            case s: ContinuousSplit => {
              val point: Double = s.threshold
              point
            }
          }
          if (tmp.bot == Double.MaxValue) {
            tmp.bot = sp
          } else if (tmp.top == Double.MinValue) {
            tmp.top = sp
          }

        }
        case Right(r) => {
          tmp.leaf = r
          if (tmp.bot == Double.MaxValue) {
            tmp.bot = Double.MinValue
          }
        }
      }
    }
    if (tmp.leaf != null) {
      if (tmp.top == Double.MinValue) {
        tmp.top = Double.MaxValue
      }
      bins.append(BinInfo((tmp.bot, tmp.top), tmp.leaf))
    }
    bins
  }


  //~----------------------------------------------------------------------

  /**
    * Extract splits
    *
    * @param model
    * @return
    */
  private[tree] def getSplits(model: DecisionTreeClassificationModel) = {
    val buf = mutable.Buffer[Split]()
    recursiveExtraSplits(model.rootNode, buf)
    buf
  }

  /**
    *
    * @param node
    */
  private[tree] def recursiveExtraSplits(node: Node, buf: mutable.Buffer[Split]): Unit = {

    if (node.isInstanceOf[LeafNode]) {
      return
    }
    node match {
      case node: InternalNode => {

        val left = node.leftChild
        if (left != null) {
          recursiveExtraSplits(left, buf)
        }
        buf.append(node.split)
        if (node.rightChild != null) {
          recursiveExtraSplits(node.rightChild, buf)
        }
      }
      case leaf: LeafNode => {
        println(s"Can not recognized node type!+${leaf.getClass.toString}")
      }
    }


  }


  /**
    * extract bins for feature
    *
    * @param model
    * @return
    */
  private[tree] def getInfo(model: DecisionTreeClassificationModel) = {
    val buf = mutable.Buffer[Either[Split, Array[Double]]]()
    recursiveExtraInfo(model.rootNode, buf)
    buf
  }


  /**
    * recursive  get ExtraInfo
    *
    * @param node
    */
  private[tree] def recursiveExtraInfo(node: Node, buf: mutable.Buffer[Either[Split, Array[Double]]]): Unit = {
    node match {
      case node: InternalNode => {

        val left = node.leftChild
        if (left != null) {
          recursiveExtraInfo(left, buf)
        }
        buf.append(Left(node.split))
        if (node.rightChild != null) {
          recursiveExtraInfo(node.rightChild, buf)
        }
      }
      case leaf: LeafNode => {
        buf.append(Right(leaf.impurityStats.stats))
      }
    }
  }

  case class BinTmpInfo(var bot: Double, var leaf: Array[Double], var top: Double)

  case class BinInfo(range: (Double, Double), hitInfo: Array[Double])

  //  case class cateBinInfo(override val range: Set[Double], override val hitInfo: Array[Double]) extends BinInfo(range = null, hitInfo) {}


  implicit def tupleToBin(db: (Double, Array[Double], Double)) = BinTmpInfo(db._1, db._2, db._3)


}
