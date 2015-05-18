--// create_table
-- Migration SQL that makes the change goes here.

DROP TABLE IF EXISTS demands
/

CREATE TABLE `demands` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL COMMENT '用户id',
  `demandcustomer` varchar(30) NOT NULL DEFAULT '' COMMENT '需求方',
  `demandcode` varchar(20) NOT NULL DEFAULT '' COMMENT '需求编号',
  `deliveryplace` varchar(30) NOT NULL DEFAULT '' COMMENT '提货地点',
  `otherplace` varchar(20) DEFAULT NULL COMMENT '其它提货地点',
  `demandamount` int(11) NOT NULL DEFAULT '0' COMMENT '需求数量',
  `deliverydate` date DEFAULT NULL COMMENT '提货时间',
  `deliverydatestart` date DEFAULT NULL COMMENT '提货时间开始',
  `deliverydateend` date DEFAULT NULL COMMENT '提货时间截至',
  `floatdays` int(11) NOT NULL DEFAULT '0' COMMENT '浮动天数',
  `deliverymode` varchar(30) NOT NULL DEFAULT '' COMMENT '提货方式',
  `unitprice` int(11) NOT NULL DEFAULT '0' COMMENT '单价',
  `inspectionagency` varchar(10) NOT NULL DEFAULT '' COMMENT '检验机构',
  `otherorg` varchar(20) DEFAULT NULL COMMENT '其它检验机构',
  `releasetime` datetime NOT NULL COMMENT '发布时间',
  `checkstatus` varchar(10) NOT NULL DEFAULT '' COMMENT '审核状态',
  `tradestatus` varchar(10) NOT NULL DEFAULT '' COMMENT '交易状态',
  `quotenum` int(11) NOT NULL DEFAULT '0' COMMENT '报价人数',
  `isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `purchasednum` int(11) NOT NULL DEFAULT '0' COMMENT '已采购量',
  `residualdemand` int(11) NOT NULL DEFAULT '0' COMMENT '剩余需求量',
  `deliverydistrict` varchar(10) NOT NULL DEFAULT '' COMMENT '提货省份片区',
  `deliveryprovince` varchar(10) NOT NULL DEFAULT '' COMMENT '提货省份',
  `isshowcompany` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示公司信息',
  `demanduuid` varchar(50) NOT NULL DEFAULT '' COMMENT '需求表uuid',
  `releasestatus` tinyint(1) NOT NULL DEFAULT '0' COMMENT '需求发布状态',
  `comment` varchar(200) DEFAULT NULL COMMENT '需求审核备注',
  `quoteenddate` date NOT NULL COMMENT '报价截至日期',
  `noshowdate` date NOT NULL COMMENT '报价截止7天后',
  `coaltype` varchar(10) NOT NULL DEFAULT '' COMMENT '煤种',
  `traderid` int(11) DEFAULT NULL COMMENT '审核员/交易员id',
  `checktime` datetime DEFAULT NULL COMMENT '审核时间',
  `NCV` int(11) NOT NULL COMMENT '低位热值',
  `ADS` decimal(4,1) DEFAULT NULL COMMENT '空干基硫份',
  `RS` decimal(4,1) NOT NULL COMMENT '收到基硫份',
  `TM` decimal(4,1) NOT NULL COMMENT '全水份',
  `IM` decimal(4,1) DEFAULT NULL COMMENT '内水份',
  `ADV` decimal(4,1) NOT NULL COMMENT '空干基挥发份',
  `RV` decimal(4,1) DEFAULT NULL COMMENT '收到基挥发份',
  `AFT` int(11) DEFAULT NULL COMMENT '灰熔点',
  `ASH` decimal(4,1) DEFAULT NULL COMMENT '灰份',
  `HGI` int(11) DEFAULT NULL COMMENT '哈氏可磨',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

--//@UNDO
-- SQL to undo the change goes here.


