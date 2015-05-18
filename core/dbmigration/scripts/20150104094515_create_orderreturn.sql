--// create_returnorder
-- Migration SQL that makes the change goes here.
-- 退货处理单

CREATE TABLE `orderreturn` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `orderid` int(11) unsigned NOT NULL COMMENT 'orders表对应的id',
  `oid` varchar(30) DEFAULT NULL COMMENT '订单编号orderid',
  `status` VARCHAR(10) DEFAULT '退货中' COMMENT '状态',
  `laststatus` varchar(10) DEFAULT NULL COMMENT '上一个状态',
  `createtime` datetime DEFAULT NULL COMMENT '创建时间',
  `lastupdatetime` TIMESTAMP COMMENT '最后一次更新时间',
  `userid` int(11) unsigned NOT NULL COMMENT '申请人id',
  `username` varchar(20) NOT NULL COMMENT '申请人name',
  `iscanceled` tinyint(1) DEFAULT '0' COMMENT '是否已经撤销退货',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

--//@UNDO
-- SQL to undo the change goes here.


