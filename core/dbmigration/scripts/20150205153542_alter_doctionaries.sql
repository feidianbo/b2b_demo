--// alter_doctionaries
-- Migration SQL that makes the change goes here.

DROP TABLE IF EXISTS `dictionaries`
/

CREATE TABLE `dictionaries` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL COMMENT '编码',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/


INSERT INTO `dictionaries` (`id`, `code`, `name`)
VALUES
	(1,'inspectionagency','通标标准技术服务有限公司(SGS)'),
	(2,'inspectionagency','中国检验认证(集团)有限公司(CCIC)'),
	(3,'inspectionagency','中国出入境检验检疫局(CIQ)'),
	(4,'inspectionagency','上海赛孚燃料检测有限公司'),
	(5,'inspectionagency','北京华夏力鸿商品检验有限公司'),
	(6,'inspectionagency','英斯贝克商品检验有限公司'),
	(7,'inspectionagency','广东省质量监督煤炭检验站'),
	(8,'coaltype','动力煤'),
	(9,'coaltype','喷吹煤'),
	(10,'coaltype','焦煤'),
	(11,'coaltype','无烟煤'),
	(12,'deliverymode','港口平仓'),
	(13,'deliverymode','场地自提'),
	(14,'deliverymode','到岸仓底')
	/

--//@UNDO
-- SQL to undo the change goes here.


