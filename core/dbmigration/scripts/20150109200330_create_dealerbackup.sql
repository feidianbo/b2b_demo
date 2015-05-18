--// create_dealerbackup
-- Migration SQL that makes the change goes here.
-- 交易员备份表

CREATE TABLE `dealerbackup` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `deliveryregion` VARCHAR(30) NOT NULL DEFAULT '' COMMENT '提货地区',
  `deliveryprovince` VARCHAR(30) NOT NULL DEFAULT '' COMMENT '提货省市',
  `deliveryplace` VARCHAR(30) NOT NULL DEFAULT '' COMMENT '提货港口',
  `dealerid` VARCHAR(30) NOT NULL DEFAULT '' COMMENT '交易员编号',
  `dealername` VARCHAR(30) NOT NULL DEFAULT '' COMMENT '交易员name',
  `iswork` tinyint(1) NOT NULL COMMENT '状态, 布尔型， 1为在职，0 为离职',
  `createtime` DATETIME DEFAULT NULL COMMENT '创建时间',
  `lastupdatetime` TIMESTAMP COMMENT '最后一次更新时间',
  `lastupdatemanid` int(11) unsigned NOT NULL COMMENT '操作人id',
  `lastupdatemanname` varchar(20) NOT NULL COMMENT '操作人name',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

--//@UNDO
-- SQL to undo the change goes here.


