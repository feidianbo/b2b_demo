--// create_myinterest
-- Migration SQL that makes the change goes here.

DROP TABLE IF EXISTS `myinterest`
/
CREATE TABLE `myinterest` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sellinfoid` int(11) unsigned NOT NULL COMMENT 'sellinfo表对应的id',
  `pid` varchar(50) NOT NULL COMMENT '产品id',
  `pname` varchar(10) DEFAULT NULL COMMENT '产品名称',
  `seller` varchar(20) DEFAULT NULL COMMENT '自营',
  `price` int(4) NOT NULL DEFAULT '0' COMMENT '价格',
  `isdelete` tinyint(1) DEFAULT '0' COMMENT '0为关注，1为取消关注',
  `userid` int(11) unsigned DEFAULT NULL,
  `createtime` datetime DEFAULT NULL COMMENT '关注生成时间',
  `lastupdatetime` datetime DEFAULT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8
/

--//@UNDO
-- SQL to undo the change goes here.


