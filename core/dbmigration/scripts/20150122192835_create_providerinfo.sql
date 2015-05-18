--// create_providerinfo
-- Migration SQL that makes the change goes here.
CREATE TABLE `providerinfo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `providername` varchar(50) DEFAULT '' COMMENT '供应商名称',
  `enterpriseproperty` varchar(30) DEFAULT '' COMMENT '企业性质',
  `enterpriseaddress` varchar(100) DEFAULT '' COMMENT '企业地点',
  `registeredcapital` decimal(20,2) DEFAULT '0.00' COMMENT '注册资本',
  `lastupdatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `createtime` datetime DEFAULT NULL COMMENT '信息创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/
--//@UNDO
-- SQL to undo the change goes here.


