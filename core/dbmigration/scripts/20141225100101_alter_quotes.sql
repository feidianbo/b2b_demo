DROP TABLE IF EXISTS `quotes`/

CREATE TABLE `quotes` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL COMMENT '用户id',
  `demandid` int(11) NOT NULL COMMENT '需求id',
  `demandcode` varchar(20) NOT NULL DEFAULT '' COMMENT '需求编号',
  `unitprice` int(11) NOT NULL DEFAULT '0' COMMENT '单价',
  `supplyton` int(11) NOT NULL DEFAULT '0' COMMENT '申供瓶数',
  `lowcalorificvalue` int(11) NOT NULL DEFAULT '0' COMMENT '热值',
  `quote` int(11) NOT NULL DEFAULT '0' COMMENT '报价',
  `lastupdatetime` datetime NOT NULL COMMENT '最后更新时间',
  `isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `status` varchar(10) NOT NULL DEFAULT '' COMMENT '状态',
  `deliverymode` varchar(10) NOT NULL DEFAULT '' COMMENT '提货方式',
  `deliverydate` date DEFAULT NULL COMMENT '提货时间',
  `deliverydatestart` date DEFAULT NULL COMMENT '自提开始时间',
  `deliverydateend` date DEFAULT NULL COMMENT '自提截止时间',
  `quoteenddate` date NOT NULL COMMENT '报价截止时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/