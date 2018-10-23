import util.SparkTools

/**
  * create by colin on 2018/9/7
  */
object TestReg extends  SparkTools {
  val pattern = "[`~!@#$%^&*()+=|{}':;'\\[\\]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。、？]".r
  val str =
    """
      |"[现金白卡小米金融,0.1,铁路12306,农行掌上银行,MadRaceVR,去哪儿旅行,美图秀秀,TruckDriver2,Construct'14,百度输入法小米版,现金巴士,钱站,先花一亿元,火山小视频,米米罐,快手小游戏,京东金融,百度,369休闲吧,秒借-极速小额借贷,牛呗借钱借款,随时现金,借得快,蛋花花,财小仙小额贷,小米VIP,支付宝,闪银,买单侠,美团,小米商城,百度网盘,SubwaySimulator2Deluxe-LondonEdition,PhotoGrid,小米风行播放插件,语音识别设置,闪电借款,YY,TalkBack,高德地图,AirportSimulator2015,手机营业厅,饿了么,QQ空间,视频电话,WiFi万能钥匙,远程协助,万能遥控,大众点评,拍拍贷借款,爱奇艺播放器,爱奇艺,58同城,手机百度,波克捕鱼,QQ邮箱,小米锁屏画报,小米视频追剧助手,手机淘宝,谷歌拼音输入法,喜马拉雅FM,米聊,美团外卖,贷上钱,优酷,秒贷,秒下款,玖富万卡,小米画报,信用白条,LT来电闪光,快手加粉,中国移动,京东,信而富,用钱宝,JJ斗地主,小牛速贷,向钱贷,融360,腾讯视频,单机游戏极品飞车,0.1;51零用钱,腾讯新闻,招联金融,米家,爱奇艺,TalkBack,美团,视频电话,京东,QQ,拼多多,小米搜狐视频播放器插件,喜马拉雅FM,快手,猎豹清理大师,语音识别设置,小米金融,贷上钱,应用宝,中国工商银行,彩库宝典,WiFi万能钥匙,携程旅行,小米PPTV播放器插件,王者荣耀,微信,折800,小米视频追剧助手,KLOBugreport,来分期,小米生活,58同城,手机百度,e乐彩,工银融e联,谷歌拼音输入法,爱奇艺播放器,万能遥控,小米画报,小米金服安全组件,百度地图,红包捕手,手机淘宝,快视频,小米商城,小米风行播放插件,小米直播助手,百度输入法小米版,UC浏览器,广东移动,支付宝,腾讯视频小米版,秒拍,酷狗音乐,米聊,内涵段子,蓝领贷,今日头条,全球上网工具插件,现金白卡,借几天,小牛闪贷,信用钱包,芒果银行,小米VIP,全民付移动支付,高德地图,你我贷借款,拍拍贷借款,曹操贷,信而富,原子贷,魔法现金,秒白条,给你花,现金巴士,钱有路,及贷,众购彩票,0.1;我的闹钟,拍拍贷借款-借款贷款,优借,闪电周转,卡乐贷,暖薪贷,顺借,支付宝安全校验服务,蓝薪卡,鲁大师评测-双开版,土豆用钱,借几天,好钱包,你我金融,千百块,UC浏览器,钱鱼借贷,钱急送,探探,众享花,易还吧,芒果金柜,好还,相机360,高德地图,鲁大师-跑分清理加速,蓝领贷,拍拍贷借款(贷款·借钱·借贷）,球球是道-足球比分直播,速援贷,财小仙小额贷,内涵段子,乐贷款,来分期,西瓜视频,中国农业银行,现金白卡,MOMO陌陌,太好借钱,掌上生活,信用钱包-贷款分期,安逸花,拍拍贷借款(贷款·借钱·借贷),星星钱袋,QQ,彩民家,球探体育比分,大众点评,缺钱么,江湖救急,有道翻译官,招联金融,百度地图,直播吧,钱有路,美团,万达贷,贷上钱,捷信金融,拍拍贷借款,UC,球球是道,现金巴士,今日头条,平安好车主,快手,鲁大师,指尖贷,映客直播,农行掌上银行,微信,陌陌,360手机助手,地铁跑酷,神庙逃亡2,曹操贷,信用钱包,正点闹钟,讯飞输入法,支付宝,快贷贷款,携程旅行,马上金融,腾讯视频,中国银行,魔法现金,广发银行,手机淘宝,一键锁屏,WiFi万能钥匙,小狐分期,优步-Uber,爱奇艺,铃声多多,Google日历同步,百度糯米,向钱贷,意见反馈,他趣,浦发信用卡,贷款机,vivo文档阅读器,信用钱包-贷款易,有钱用,拍拍贷借款(贷款·借钱),0.1;贝勒爷-贷款借钱借款,闪电贷款王,饿了么,mo9信用钱包,向钱贷,唱吧,现金白卡宝,19楼,借点钱,借钱花呗,现金巴士,搜狗输入法,一刻千金,现金白卡, 360手机卫士,信而富,腾讯视频,简单借款,我来贷,趣分期,越狱搜索,翼支付,拍拍贷借款,掌上生活,WiFi万能钥匙,贷上钱,VUE,网易邮箱,360借条,信用钱包-贷款易,Keep,微信,手机淘宝,薄荷,Airbnb,京东,TouchTimer,美团外卖,金华行,支付宝,手雷,酷狗音乐,美图秀秀,分期乐,爱奇艺,桔子分期,万达贷,KC网络电话,去哪儿旅行,趣店,钉钉,拍拍贷借款-借钱借贷,安逸花,零零花,花花白卡,乐宝贷,暖薪贷,零零期分期,大粒贷,现金卡,途牛旅游,来分期,壹现金,0.1;QQ安全中心,龙珠直播,中国建设银行,拍拍贷借款,酷狗音乐,腾讯新闻,电信营业厅,贷上钱,TalkBack,高德地图,电商助手,语音设置,KLOBugreport,捷信金融,DNF掌游宝,小米WiFi,小米金服安全组件,UC浏览器,QQ,微信,支付宝,平安金管家,小米商城,小米锁屏画报,小米PPTV播放器插件,现金快贷,QQ音乐,小米风行播放插件,爱奇艺播放器,DNF助手,缺钱么,小米搜狐视频播放器插件,小米画报,百度翻译,游戏中心剑侠世界2主题包,和家亲,爱拍原创,QQ浏览器,应用宝,腾讯视频, 追书神器 ,全民K歌, 懒人听书 ,喜马拉雅FM,先花一亿元,用钱宝,现金巴士,搜狗浏览器,中国移动,搜狗搜索加强版,蓝领贷,手机贷-贷款借钱,信用钱包-贷款易,现金白卡,玖富叮当,拍拍现金贷,快速借款,借得快,中国银行,闪贷侠,快贷,借钱快,闪电借款,闪电借款极速版,捷信大福贷,江湖救急,快视频,迅雷影音,小米直播助手,阿拉德之怒,欢乐斗地主,语音识别设置,手机淘宝,现金借款,安逸花,快手,阅读,迅雷,手机贷,向钱贷,小米金融,第一彩票,重庆时时彩,融360,快贷（贷款.借钱.借贷）,速贷之家-贷款借钱,宜人贷借款,今日头条,新浪有借,51信用卡管家,闪银,中安信业贷款,小赢卡贷,贷款宝借款,捷信福袋,借钱用,乐贷款,你我贷借款,帮你借,现金超人,钱速来,宋江贷,贷款口子,现金速递,全本小说下载器,闪贷-贷款借款分期,贷款钱包-极速放款,上鱼,91钓鱼,阿里邮箱,极速现金侠,卡牛信用卡管家,小米视频追剧助手,功夫贷,极速钱包,及贷,布丁小贷,曹操贷,贷款-借钱宝,借贷宝,信而富,绿洲闪贷,拉卡拉,省呗,淘钱宝,拿下钱包,容易贷信用贷款,贷款黑卡,还借钱,0.1;借了吗,51信用卡管家,拍拍贷借款,腾讯地图,拼多多,你我贷借款,瓜子二手车,全民K歌,手机淘宝,京东金融,2345贷款王,QQ安全中心,汽车之家,QQ,MAKA,全民飞机大战,QQ音乐,企业微信,百度输入法,易企秀,即刻贷-极速放款,QQ浏览器,游戏中心,百度地图,QQ同步助手,贷上钱,网易邮箱大师,二手车直卖网,闪电借款,极速现金侠,闪电周转,相册管家,支付宝,计算器,录音宝,山东移动,京东,账号之家,TIM,闪银借款,QQ邮箱,招联金融,融360,10086,天气,手机管家,迅雷,现金魔方,微博,UC浏览器,应用宝,51零用钱,安全中心,菱菱邦,闪贷借款,微贷,二手车之家,转转,微信,酷狗音乐,愚人贷,360手机助手,微店买家版,卡乐贷,净化大师,58同城,好运万年历,捷信金融,来分期,手机百度,现金巴士,阿里巴巴,宜人贷借款,借贷宝,现金白卡,脉脉,爱奇艺,讯飞输入法,火山小视频,166彩票,B612咔叽,短信群发,山东农信,随芯用,快手,虎牙直播,微众银行,腾讯视频,酷狗直播,千赢国际,西瓜视频,千赢DT,优酷,大白汽车分期,酷狗唱唱,MOMO陌陌,连接助理,玖富万卡,360贷款导航,闪电贷,51小额贷款助手,0.1;闪电借款,信用白条,360借条,51信用卡管家,我来贷,星星钱袋,天天P图,借钱快,薪火分享神器,新浪有借,现金借钱贷款,支付宝安全校验服务,汽车报价大全,美团,环球黑卡,美借,中国工商银行,点融魔借,好易借,博雅斗地主,天天斗地主（真人版）,你我贷借款,中国农业银行,2345贷款王-借钱借款,WiFi万能钥匙,铁友火车票12306抢票,阿里邮箱,去哪儿旅行,铃声多多,中信银行,畅行临沂,探探,百度地图,贷上钱,米聊,滴滴出行,货拉拉-搬家拉货,招联金融,网商银行,手机淘宝,贷款-贷款贷款大小贷,微信,王者荣耀,山东农信,滴滴司机,HiGo出租,滴滴车主,QQ,平安普惠,QQ邮箱,贷款王（借钱.借贷.借款）,开心消消乐®,开吧,邮储银行,教育收费,MOMO陌陌,高德地图,支付宝,微车违章查询,拍拍贷借款(贷款·借钱·借贷),脉脉,酷狗音乐,Google日历同步,一键锁屏,快手,今日头条,云视通,融360,手机百度,农行掌上银行,京东金融,互传,布丁信用,布丁小贷,信用钱包,借得快,京东,动卡空间,玖富万卡,卡牛信用卡管家,大小贷,钉钉,百度,欢乐斗地主,中国建设银行,信用人生,考拉征信,0.1;小花钱包,手机淘宝,优酷,乐视视频,腾讯新闻,融360,QQ,爱奇艺,QQ同步助手,芒果TV,饥饿鲨：进化,企鹅FM,新疆移动手机营业厅,有声小说,美图秀秀,菜鸟裹裹,全民K歌,潮自拍,WiFi万能钥匙,支付宝快捷支付服务,飞猪,天猫,支付宝,蚂蚁聚宝,酷狗音乐,快手,宜搜小说,UC浏览器,美团,QQ音乐,滴滴出行,给你花,欢乐斗地主（二人版）,贷上钱,腾讯视频,MOMO陌陌,闪贷-贷款借款分期,开心消消乐®,微信,美颜相机,融360贷款,屌丝贷,蓝领贷,铁路12306,绿洲闪贷,立马有钱,去哪儿旅行,云闪付,奖多多彩票,你我金融贷款借钱,高德地图,58同城,拼多多,JJ斗地主,向钱贷,宾果消消消,大粒贷,财富的士,好享瘦,现金巴士,京东,脉脉,天神贷,极速现金侠,京东金融,闪电借款,闪电周转,来分期,信而富,王者荣耀,全能借款,给你花贷款,来电闪光灯,QQ邮箱,爱奇艺头条,极速借钱贷款借款分期,正好花,我来贷,慧融贷款,你我金融,消灭星星经典版,银联钱包,正好分期,原子贷,0.1;你我贷理财,极速钱包,惠借贷款,现金超市,贷款快,51信用卡管家,芝麻借钱,小赢卡贷,及贷,贷款王极速版,贷款啦,信用钱包-贷款易,玖富万卡,借点钱贷款,第一贷款,百度钱包,贷款宝借款-小额贷款,快贷贷款,融360贷款,现金快贷,快金,鼎及贷,功夫贷借款,乐宝袋,神灯小贷,懒人听书-小说电台,百度,QQ浏览器,赶集网,贪吃蛇大作战,芒果TV, PP助手,酷狗音乐,乐橙,融合,懒人听书,美团外卖,UC浏览器,百姓网,爱奇艺头条,快手,你我金融,动卡空间,火贷款手机借钱,小额借钱,贷款宝借款,贷款,路路贷,极速现金侠,好贷,借钱快,用钱宝,猎融贷款,闪借款,苏宁金融,飞速贷款,蜡笔分期,借得快,花无缺上班贷,信而富,手机贷款,发薪贷借钱贷款,小额借贷,差钱吗,信用白条,财鸟贷款,51人品贷,极速贷款小额借款,现金贷款快速借款,91贷款,贷嘛,分利宝,火速贷款,小额信用贷款,爱钱进,现金借款平台,急用钱,闪电贷款,安逸花,魂斗罗:归来,唱吧,爱信钱包,借钱用,益秒到,借易贷,小葱钱包,信用贷款-信任贷款,速贷贷款,贷款60秒,E都市钱包,现金周转,小钱,小额贷款王,贷款助手,小额贷款,一秒贷款,缺钱么,速贷借呗,米米贷,指尖钱包,猎马贷款,布丁小贷,恒易贷,一信贷,花无忧,闪电周转,千百块,贷你嗨,财付通,还卡超人,极速贷款,向钱贷,亲亲小贷,贷款王,指尖贷,分期贷款,急用钱ATM,来借,功夫贷贷款,简融现金贷款王,贷呗,有贝钱袋,贷款APP,职钱,麻辣小额贷款,顺借,小花急用贷,救急钱包,mo9信用钱包,人人贷借款,麦芽贷,垫付宝,有鱼贷款,懒人贷款,老司机借钱,现金巴士,小花钱包,急借通,贷款钱包,掌上小额贷款网,财小仙小额贷,流金贷,返利,秒借-信用贷款借款,魔法现金,如意借,阳光惠生活,员工版,全民K歌,急用钱贷款,包银消费, 赶集网,交管12123,工银融e联,贷款管家,3D冲上云霄,招联金融,斐讯路由, 懒人听书 ,车轮驾考通,你我贷借款,2345贷款王,顺丰金融,中国建设银行,WiFi万能钥匙,拼多多,闪电借款,快捷支付,9188彩票,速贷,闲鱼,乐贷款,手机淘宝,融360,我来贷,信用管家,微信,省呗,MopriaPrintService,TXT免费全本阅读器,差钱吗_小额贷款,丰声,i贷贷款专业版,58同城,天天快报,爱奇艺,欢乐麻将,百度地图,网易将军令,快跑者配送端,趣头条,微信支付安全插件（百度）,功夫贷,拍拍贷借款,贷上钱,王者荣耀,中国工商银行,平安金管家,百度手机助手,全民夺宝新春版,手机百度,应用宝,平安好医生,优酷,欢乐斗地主,顺手学,梦幻藏宝阁,立刷,QQ,QQ音乐,梦幻互通版,RealRacing3,支付宝,拍拍贷借款(贷款·借钱),顺丰大当家,QQ邮箱,借贷-现金借款平台,邮储银行,钞市,菠萝贷,51借钱--信用贷款,钱有路,拉卡拉,豆豆钱,星星钱袋,51返呗,福彩快3彩票,马上金融,闪银,手机贷-贷款借钱借款,闪贷-贷款借款分期,奇速贷,0.1;91借钱极速放款,借款王贷款借钱,极速贷款,借钱快,指尖钱包,下钱快,小葱钱包,你我贷借款,快速借款,魔法现金,及贷,现金巴士,信而富,现金借款,布丁小贷,水象分期,亲亲小贷,信用钱包,手机贷-贷款借钱借款,发薪贷借钱贷款,一信贷,乐贷款,京东金融,热血江湖,西瓜视频,手机营业厅,中国工商银行,高德地图,开心钱包,UC浏览器,平安金管家,蓝领贷,原子贷,微当贷款,奇速贷,花薪闪借,应用宝,拍拍贷借款(贷款·借钱),QQ,Google日历同步,贪吃蛇大作战,球球大作战,一键锁屏,互传,微信,汤姆猫跑酷,支付宝,玖富叮当,意见反馈,皮皮跑胡子,天龙八部,魂斗罗:归来,酷狗音乐,闪银,迅雷,WiFi万能钥匙,优酷,腾讯视频,招联金融,UC,手机京东,开心消消乐®,贷上钱,QQ邮箱,天天象棋,百度地图,书旗小说,钱包贷,贷款王（借钱.借贷.借款）,简单借款,米米贷,借乐花,极速现金侠,菠萝贷, 懒人听书 ,分期借贷-小额贷款,点点速借,指尖贷,你我金融,98彩票网,小信用,极速钱包,51零用钱,扎金花真欢乐,盈盈有钱,贝壳钱"
    """.stripMargin

val tt = pattern replaceAllIn(str, "")
  println()

    spark.sql(
      s"""
        |select '$tt' as tf from test
      """.stripMargin).show()
}
