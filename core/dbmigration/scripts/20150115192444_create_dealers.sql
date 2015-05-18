--// create_dealers
-- Migration SQL that makes the change goes here.

DROP TABLE IF EXISTS dealers
/
CREATE TABLE `dealers` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `deliveryregion` varchar(30) DEFAULT NULL COMMENT '提货地区',
  `deliveryprovince` varchar(30) DEFAULT NULL COMMENT '提货省市',
  `deliveryplace` varchar(30) DEFAULT NULL COMMENT '提货港口',
  `dealerid` varchar(30) DEFAULT NULL COMMENT '交易员编号',
  `dealername` varchar(30) DEFAULT NULL COMMENT '交易员name',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastupdatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `lastupdatemanid` int(11) unsigned DEFAULT NULL COMMENT '操作人id',
  `lastupdatemanname` varchar(20) DEFAULT NULL COMMENT '操作人name',
  `uuid` varchar(50) DEFAULT NULL COMMENT 'uuid',
  `dealerphone` varchar(15) DEFAULT NULL COMMENT '交易员电话',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8
/
INSERT INTO dealers(deliveryregion, deliveryprovince, deliveryplace, dealerid, dealername, dealerphone, createtime, lastupdatemanid, lastupdatemanname) VALUES('测试', '测试', '测试', 'JYY000', '测试', '021-88888888', now(), 0, 'admin')
/

--//@UNDO
-- SQL to undo the change goes here.


