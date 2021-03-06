--// alter_areaports
-- Migration SQL that makes the change goes here.

DROP TABLE IF EXISTS `areaports`
/

CREATE TABLE `areaports` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '名称',
  `parentid` int(11) NOT NULL DEFAULT '0' COMMENT '父id',
  `code` varchar(10) NOT NULL DEFAULT '' COMMENT '归类编码',
  `isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除(禁用)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

INSERT INTO `areaports` (`id`, `name`, `parentid`, `code`, `isdelete`)
VALUES
	(1,'华北地区',0,'area',0),
	(2,'华东地区',0,'area',0),
	(3,'华中地区',0,'area',0),
	(4,'华南地区',0,'area',0),
	(5,'河北省',1,'province',0),
	(6,'天津市',1,'province',0),
	(7,'山东省',2,'province',0),
	(8,'江苏省',2,'province',0),
	(9,'上海市',2,'province',0),
	(10,'安徽省',2,'province',0),
	(11,'浙江省',2,'province',0),
	(12,'福建省',2,'province',0),
	(13,'江西省',3,'province',0),
	(14,'湖北省',3,'province',0),
	(15,'湖南省',3,'province',0),
	(16,'广东省',4,'province',0),
	(17,'广西自治区',4,'province',0),
	(18,'海南省',4,'province',0),
	(19,'秦皇岛港',5,'port',0),
	(20,'曹妃甸港',5,'port',0),
	(21,'京唐港',5,'port',0),
	(22,'黄骅港',5,'port',0),
	(23,'天津港',6,'port',0),
	(24,'前湾港',7,'port',0),
	(25,'日照港',7,'port',0),
	(26,'龙口港',7,'port',0),
	(27,'青岛港',7,'port',0),
	(28,'东营港',7,'port',0),
	(29,'蓬莱港',7,'port',0),
	(30,'潍坊港',7,'port',0),
	(31,'烟台港',7,'port',0),
	(32,'威海港',7,'port',0),
	(33,'岚山港',7,'port',0),
	(34,'海阳港',7,'port',0),
	(35,'大湾港',7,'port',0),
	(36,'莱州港',7,'port',0),
	(37,'万寨港',8,'port',0),
	(38,'邳州港',8,'port',0),
	(39,'南通港',8,'port',0),
	(40,'连云港',8,'port',0),
	(41,'射阳港',8,'port',0),
	(42,'大丰港',8,'port',0),
	(43,'华能太仓港',8,'port',0),
	(44,'如皋港',8,'port',0),
	(45,'张家港',8,'port',0),
	(46,'国信秦港',8,'port',0),
	(47,'扬子江港',8,'port',0),
	(48,'太和港',8,'port',0),
	(49,'江阴港',8,'port',0),
	(50,'泰州港',8,'port',0),
	(51,'海昌码头(扬州)',8,'port',0),
	(52,'镇江港',8,'port',0),
	(53,'扬州港',8,'port',0),
	(54,'西坝码头(南京)',8,'port',0),
	(55,'罗泾港',9,'port',0),
	(56,'洋山港',9,'port',0),
	(57,'外高桥港',9,'port',0),
	(58,'马鞍山港',10,'port',0),
	(59,'芜湖港',10,'port',0),
	(60,'铜陵港',10,'port',0),
	(61,'安庆港',10,'port',0),
	(62,'北仑港',11,'port',0),
	(63,'温州港',11,'port',0),
	(64,'舟山港',11,'port',0),
	(65,'台州港',11,'port',0),
	(66,'宁海港',11,'port',0),
	(67,'象山港',11,'port',0),
	(68,'石浦港',11,'port',0),
	(69,'乍浦港',11,'port',0),
	(70,'莆田港(东吴)',12,'port',0),
	(71,'可门港',12,'port',0),
	(72,'厦门港',12,'port',0),
	(73,'泉州港',12,'port',0),
	(74,'福州港',12,'port',0),
	(75,'湄洲港',12,'port',0),
	(76,'九江港',13,'port',0),
	(77,'丰城港',13,'port',0),
	(78,'南昌港',13,'port',0),
	(79,'枝城港',14,'port',0),
	(80,'荆州港',14,'port',0),
	(81,'鄂州港',14,'port',0),
	(82,'黄石港',14,'port',0),
	(83,'宜昌港',14,'port',0),
	(84,'石首港',14,'port',0),
	(85,'武惠堤码头',14,'port',0),
	(86,'城陵矶港',15,'port',0),
	(87,'广州港(新沙)',16,'port',0),
	(88,'广州港(西基)',16,'port',0),
	(89,'广州港(黄埔)',16,'port',0),
	(90,'海昌码头(东莞)',16,'port',0),
	(91,'湛江港',16,'port',0),
	(92,'珠海港',16,'port',0),
	(93,'惠州港',16,'port',0),
	(94,'海腾码头',16,'port',0),
	(95,'阳江港',16,'port',0),
	(96,'中山港',16,'port',0),
	(97,'江门港',16,'port',0),
	(98,'汕头港',16,'port',0),
	(99,'佛山港',16,'port',0),
	(100,'海门港',16,'port',0),
	(101,'茂名港',16,'port',0),
	(102,'防城港',17,'port',0),
	(103,'钦州港',17,'port',0),
	(104,'北海港',17,'port',0),
	(105,'贵港',17,'port',0),
	(106,'南宁港',17,'port',0),
	(107,'天盛码头',17,'port',0),
	(108,'海口港',18,'port',0),
	(109,'山西省',1,'province',0),
	(110,'内蒙古',1,'province',0),
	(111,'河南省',3,'province',0)
	/


--//@UNDO
-- SQL to undo the change goes here.


