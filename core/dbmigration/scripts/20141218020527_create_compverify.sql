--// create_compverify
-- Migration SQL that makes the change goes here.

SET FOREIGN_KEY_CHECKS = 0
/
DROP TABLE IF EXISTS `compverify`
/
CREATE TABLE `compverify` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `status` varchar(10) DEFAULT NULL COMMENT '待审核，已审核，审核未通过',
  `verifyman` varchar(20) DEFAULT NULL COMMENT '审核人',
  `applytime` datetime DEFAULT NULL COMMENT '申请时间',
  `verifytime` datetime DEFAULT NULL COMMENT '审核通过时间',
  `companyid` int(11) unsigned NOT NULL COMMENT '公司id',
  `userid` int(11) unsigned NOT NULL COMMENT '用户id',
  `remarks` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8
/

--//@UNDO
-- SQL to undo the change goes here.


