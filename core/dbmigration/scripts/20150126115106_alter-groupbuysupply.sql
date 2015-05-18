--// alter-groupbuysupply
-- Migration SQL that makes the change goes here.
drop table `groupbuysupply`
/
CREATE TABLE `groupbuysupply` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uuid` varchar(50) DEFAULT NULL COMMENT 'UUID',
  `createtime` datetime DEFAULT NULL COMMENT '信息创建时间',
  `lastupdatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `NCV` int(5) unsigned NOT NULL COMMENT '收到基低位发热量',
  `ADS` decimal(3,1) DEFAULT NULL COMMENT '空气干燥基全硫',
  `RS` decimal(3,1) NOT NULL COMMENT '收到基全硫',
  `TM` decimal(4,1) NOT NULL COMMENT '全水 0-100',
  `IM` decimal(4,1) DEFAULT NULL COMMENT '内水 0-100',
  `ADV` decimal(4,1) NOT NULL COMMENT '空气干燥基挥发份',
  `RV` decimal(4,1) DEFAULT NULL COMMENT '收到基挥发份',
  `ASH` decimal(4,1) DEFAULT NULL COMMENT '收到基灰分',
  `AFT` int(4) unsigned DEFAULT NULL COMMENT '灰熔点，软化温度',
  `HGI` int(4) DEFAULT NULL COMMENT '哈氏可磨',
  `providerinfoid` int(11) unsigned NOT NULL COMMENT '团购供应商信息id',
  `coaltype` varchar(10) DEFAULT '' COMMENT '煤种',
  `port` varchar(20) DEFAULT '' COMMENT '港口',
  `groupbuybegindate` datetime DEFAULT NULL COMMENT '团购开始时间',
  `groupbuyenddate` datetime DEFAULT NULL COMMENT '团购结束时间',
  `marketprice` decimal(20,2) DEFAULT '0.00' COMMENT '市场价格',
  `groupbuyprice` decimal(20,2) DEFAULT '0.00' COMMENT '团购价格',
  `deliveryplace` varchar(100) DEFAULT '' COMMENT '交货地点',
  `storageplace` varchar(100) DEFAULT '' COMMENT '堆位',
  `deliverytime` datetime DEFAULT NULL COMMENT '交货时间',
  `dliverymode` varchar(20) DEFAULT '' COMMENT '交货方式',
  `supplyamount` int(11) DEFAULT '0' COMMENT '供应数量',
  `selledamount` int(11) DEFAULT '0' COMMENT '已销售量',
  `inspectionagency` varchar(30) DEFAULT '' COMMENT '检验机构',
  `surplusamount` int(11) DEFAULT '0' COMMENT '可销售库存',
  `minimumamount` int(11) DEFAULT '0' COMMENT '起订量',
  `enddate` datetime DEFAULT NULL COMMENT '团购截止日期',
  `isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/
/
--//@UNDO
-- SQL to undo the change goes here.


