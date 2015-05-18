--// alter-groupbuyorder
-- Migration SQL that makes the change goes here.
drop table `groupbuyorder`
/
CREATE TABLE `groupbuyorder` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uuid` varchar(50) DEFAULT NULL COMMENT 'UUID',
  `groupbuysupplyid` int(11) unsigned NOT NULL COMMENT '团购供货信息id',
  `userid` int(11) unsigned NOT NULL COMMENT '用户id',
  `qualificationcode` varchar(20) DEFAULT '' COMMENT '团购资质编号',
  `groupbuyordercode` varchar(20) DEFAULT '' COMMENT '团购订单编号',
  `groupbuyorderstatus` varchar(20) DEFAULT '' COMMENT '团购订单状态',
  `volume` int(11) NOT NULL DEFAULT '0' COMMENT '成交量',
  `lastupdatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `createtime` datetime DEFAULT NULL COMMENT '信息创建时间',
  `isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `contractverify` tinyint(1) DEFAULT '0' COMMENT '合同确认',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/

--//@UNDO
-- SQL to undo the change goes here.


