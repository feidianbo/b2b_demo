--// create_orders
-- Migration SQL that makes the change goes here.

DROP TABLE IF EXISTS `orders`
/
CREATE TABLE `orders` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `pid` varchar(50) NOT NULL COMMENT '产品id',
  `sellinfoid` int(11) unsigned NOT NULL COMMENT 'sellinfo表对应的id',
  `status` varchar(10) DEFAULT NULL COMMENT '订单状态',
  `amount` int(6) unsigned DEFAULT NULL COMMENT '数量',
  `createtime` datetime DEFAULT NULL COMMENT '订单生成时间',
  `lastupdatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `deliverytime1` datetime DEFAULT NULL COMMENT '提货时间1',
  `deliverytime2` datetime DEFAULT NULL COMMENT '提货时间2',
  `seller` varchar(30) DEFAULT NULL COMMENT '卖家，自营还是其他卖家',
  `deliveryplace` varchar(20) DEFAULT NULL COMMENT '提货地',
  `deliverymode` varchar(30) DEFAULT NULL COMMENT '提货方式',
  `transportmode` varchar(20) DEFAULT NULL COMMENT '运输方式',
  `totalmoney` decimal(20,2) DEFAULT NULL COMMENT '总货款',
  `linkman` varchar(20) DEFAULT NULL COMMENT '联系人',
  `linkmanphone` varchar(11) DEFAULT NULL COMMENT '联系人电话',
  `price` decimal(6,2) DEFAULT NULL COMMENT '单价 /瓶',
  `userid` int(11) unsigned DEFAULT NULL COMMENT '用户id，下单人id',
  `ordertype` varchar(10) DEFAULT NULL COMMENT '订单类型，自营单，委托单',
  `uuid` varchar(50) DEFAULT NULL COMMENT 'UUID',
  `orderid` varchar(50) DEFAULT NULL COMMENT 'Order Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8
/

--//@UNDO
-- SQL to undo the change goes here.


