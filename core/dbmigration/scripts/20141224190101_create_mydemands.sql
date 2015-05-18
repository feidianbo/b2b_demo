CREATE TABLE `mydemands` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL COMMENT '用户id',
  `demandcode` varchar(30) NOT NULL DEFAULT '' COMMENT '需求编号',
  `releasetime` datetime NOT NULL COMMENT '发布时间',
  `quoteenddate` date NOT NULL COMMENT '报价截止时间',
  `demandamount` int(11) NOT NULL DEFAULT '0' COMMENT '需求瓶数',
  `quotenum` int(11) NOT NULL DEFAULT '0' COMMENT '报价人数',
  `purchasednum` int(11) NOT NULL DEFAULT '0' COMMENT '已采购量',
  `status` varchar(10) NOT NULL DEFAULT '' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/