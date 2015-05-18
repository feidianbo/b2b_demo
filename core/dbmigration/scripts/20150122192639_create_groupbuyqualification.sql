--// create_groupbuyqualification
-- Migration SQL that makes the change goes here.
CREATE TABLE `groupbuyqualification` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL COMMENT '用户id',
  `qualificationcode` varchar(20) DEFAULT '' COMMENT '团购资质编号',
  `qualificationstatus` varchar(30) NOT NULL DEFAULT '' COMMENT '团购资质状态',
  `margins` decimal(20,2) DEFAULT '0.00' COMMENT '保证金',
  `lastupdatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `createtime` datetime DEFAULT NULL COMMENT '信息创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

--//@UNDO
-- SQL to undo the change goes here.


