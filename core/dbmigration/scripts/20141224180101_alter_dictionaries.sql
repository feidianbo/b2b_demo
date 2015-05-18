DROP TABLE IF EXISTS `dictionaries`/

CREATE TABLE `dictionaries` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL COMMENT '编码',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

INSERT INTO `dictionaries` (`id`, `code`, `name`)
VALUES
	(1,'region','广州港'),
	(2,'region','防城港'),
	(3,'region','罗泾港'),
	(4,'region','万寨岗'),
	(5,'region','钦州港'),
	(6,'region','秦皇岛港'),
	(7,'region','天津港'),
	(8,'region','京唐港'),
	(9,'district','华东地区'),
	(10,'district','华中地区'),
	(11,'district','华北地区'),
	(12,'district','华南地区'),
	(13,'EC_province','上海市'),
	(14,'EC_province','江苏省'),
	(15,'EC_province','安徽省'),
	(16,'EC_province','浙江省'),
	(17,'EC_province','福建省'),
	(18,'EC_province','山东省'),
	(19,'CC_province','湖北省'),
	(20,'CC_province','湖南省'),
	(21,'CC_province','河南省'),
	(22,'CC_province','江西省'),
	(23,'NC_province','北京市'),
	(24,'NC_province','天津市'),
	(25,'NC_province','河北省'),
	(26,'NC_province','山西省'),
	(27,'NC_province','内蒙古'),
	(28,'SC_province','广东省'),
	(29,'SC_province','广西'),
	(30,'SC_province','海南省'),
	(31,'deliverymode','港口平仓'),
	(32,'deliverymode','场地自提'),
	(33,'deliverymode','到岸仓底'),
	(34,'inspectionagency','CCIC'),
	(35,'inspectionagency','SGS'),
	(36,'inspectionagency','华夏力鸿'),
	(37,'inspectionagency','广东省煤检'),
	(38,'coaltype','动力煤'),
	(39,'coaltype','喷吹煤'),
	(40,'coaltype','焦煤'),
	(41,'coaltype','无烟煤')
	/