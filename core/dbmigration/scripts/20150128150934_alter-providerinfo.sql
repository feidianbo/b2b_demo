--// alter-providerinfo
-- Migration SQL that makes the change goes here.
drop table providerinfo
/
CREATE TABLE `providerinfo` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uuid` varchar(50) DEFAULT NULL COMMENT 'UUID',
  `providername` varchar(50) DEFAULT '' COMMENT '供应商名称',
  `enterpriseproperty` varchar(30) DEFAULT '' COMMENT '企业性质',
  `enterpriseaddress` varchar(100) DEFAULT '' COMMENT '企业地点',
  `registeredcapital` decimal(20,2) DEFAULT '0.00' COMMENT '注册资本',
  `contacter` varchar(30) DEFAULT '' COMMENT '联系人',
  `contactphone` varchar(20) DEFAULT '' COMMENT '联系电话',
  `isdelete` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `lastupdatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
  `createtime` datetime DEFAULT NULL COMMENT '信息创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8
/

--//@UNDO
-- SQL to undo the change goes here.


