--// alter-groupbuyqualification
-- Migration SQL that makes the change goes here.

drop table `groupbuyqualification`
/
CREATE TABLE `groupbuyqualification` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL COMMENT '用户id',
  `uuid` varchar(50) DEFAULT NULL COMMENT 'UUID',
  `qualificationcode` varchar(20) NOT NULL COMMENT '团购资质编号',
  `groupbuyordercode` varchar(20) DEFAULT '' COMMENT '团购订单编号',
  `qualificationstatus` varchar(30) NOT NULL DEFAULT '' COMMENT '团购资质状态',
  `margins` decimal(20,2) DEFAULT '0.00' COMMENT '保证金',
  `photopath` varchar(20) DEFAULT '' COMMENT '保证金图片路径',
  `lastupdatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `createtime` datetime DEFAULT NULL COMMENT '信息创建时间',
  `isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `contractverify` tinyint(1) DEFAULT '0' COMMENT '合同确认',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/
/
--//@UNDO
-- SQL to undo the change goes here.


