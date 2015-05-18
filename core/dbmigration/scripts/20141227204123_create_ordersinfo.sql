--// create_ordersinfo
-- Migration SQL that makes the change goes here.
DROP TABLE IF EXISTS `ordersinfo`
/
CREATE TABLE `ordersinfo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `status` varchar(10) DEFAULT NULL COMMENT '订单的状态',
  `operateman` varchar(20) DEFAULT NULL COMMENT '操作人',
  `operatemanid` int(11) unsigned NOT NULL COMMENT '操作人id',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastupdatetime` TIMESTAMP COMMENT '最后一次更改时间',
  `oid` int(11) unsigned NOT NULL COMMENT 'order表 id',
  `orderid` varchar(30) DEFAULT NULL COMMENT 'Order Id',
  `remarks` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

--//@UNDO
-- SQL to undo the change goes here.


