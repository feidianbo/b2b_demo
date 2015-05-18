--// create_finance
-- Migration SQL that makes the change goes here.

CREATE TABLE `finance` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(10) NOT NULL DEFAULT '' COMMENT '融资方式(快融通,煤易贷)',
  `companyname` varchar(30) NOT NULL DEFAULT '' COMMENT '公司名称',
  `address` varchar(50) NOT NULL DEFAULT '' COMMENT '公司地址',
  `businessarea` varchar(50) NOT NULL DEFAULT '' COMMENT '业务区域',
  `amount` int(11) NOT NULL COMMENT '融资额度',
  `contact` varchar(20) NOT NULL DEFAULT '' COMMENT '联系人',
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '联系电话',
  `createtime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

--//@UNDO
-- SQL to undo the change goes here.


