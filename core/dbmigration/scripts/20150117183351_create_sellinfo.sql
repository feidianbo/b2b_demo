--// create_sellinfo
-- Migration SQL that makes the change goes here.

DROP TABLE IF EXISTS `sellinfo`
/
CREATE TABLE `sellinfo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `pid` varchar(30) DEFAULT '0' COMMENT '产品id',
  `status` varchar(10) DEFAULT NULL COMMENT '需求信息状态',
  `pname` varchar(30) DEFAULT '' COMMENT '产品名称',
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
  `ykj` int(4) unsigned NOT NULL DEFAULT '0' COMMENT '一口价 瓶/元',
  `seller` varchar(20) DEFAULT NULL COMMENT '自营还是其他商家',
  `deliveryplace` varchar(30) NOT NULL DEFAULT '' COMMENT '提货地',
  `deliverytime1` datetime NOT NULL COMMENT '提货时间1',
  `deliverytime2` datetime NOT NULL COMMENT '提货时间2',
  `supplyquantity` decimal(10,0) NOT NULL,
  `soldquantity` decimal(10,0) DEFAULT NULL,
  `availquantity` decimal(10,0) DEFAULT NULL,
  `inspectorg` varchar(50) NOT NULL DEFAULT '' COMMENT '检验机构',
  `lastupdatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `createtime` datetime DEFAULT NULL COMMENT '信息发布时间',
  `sellerid` int(11) unsigned NOT NULL COMMENT '卖家id',
  `deliverymode` varchar(30) NOT NULL DEFAULT '',
  `deliveryregion` varchar(30) NOT NULL DEFAULT '' COMMENT '提货地区',
  `deliveryprovince` varchar(30) NOT NULL DEFAULT '' COMMENT '提货省市',
  `uuid` varchar(50) DEFAULT NULL COMMENT 'UUID',
  `remarks` varchar(50) DEFAULT NULL COMMENT '备注',
  `otherharbour` varchar(20) DEFAULT NULL COMMENT '其它港口',
  `otherinspectorg` varchar(20) DEFAULT NULL COMMENT '其它检验机构',
  `jtjlast` int(4) DEFAULT NULL COMMENT '阶梯价，最低价',
  `createdate` date DEFAULT NULL COMMENT '订单生成日期',
  `dealerid` varchar(30) DEFAULT NULL COMMENT '交易员编号',
  `dealername` varchar(30) DEFAULT NULL COMMENT '交易员name',
  `dealerphone` varchar(30) DEFAULT NULL COMMENT '交易员phone',
  `verifytime` datetime DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

--//@UNDO
-- SQL to undo the change goes here.


