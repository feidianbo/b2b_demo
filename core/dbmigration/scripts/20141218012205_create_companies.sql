--// create_companies
-- Migration SQL that makes the change goes here.

CREATE TABLE `companies` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '',
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `fax` varchar(30) DEFAULT NULL,
  `contact` varchar(30) DEFAULT NULL COMMENT '联系人',
  `contactphone` varchar(30) DEFAULT NULL COMMENT '联系人电话',
  `legalperson` varchar(20) DEFAULT NULL COMMENT '公司法人',
  `businesslicense` varchar(255) DEFAULT NULL COMMENT '营业执照',
  `identificationnumber` varchar(255) DEFAULT NULL COMMENT '纳税人识别号',
  `organizationcode` varchar(255) DEFAULT NULL COMMENT '组织机构代码',
  `operatinglicense` varchar(255) DEFAULT NULL COMMENT '煤炭经营许可证',
  `userid` int(10) unsigned DEFAULT NULL COMMENT '外键，users.id',
  `verifystatus` varchar(10) DEFAULT '待审核' COMMENT '公司信息审核状态',
  `remarks` varchar(50) DEFAULT NULL COMMENT '审核未通过理由',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8
/

--//@UNDO
-- SQL to undo the change goes here.


