--// create_admins
-- Migration SQL that makes the change goes here.
drop table compverify;
CREATE TABLE `compverify` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `status` varchar(10) DEFAULT NULL COMMENT '待审核，已审核，审核未通过',
  `verifyman` varchar(20) DEFAULT NULL COMMENT '审核人',
  `applytime` DATETIME DEFAULT NULL COMMENT '申请时间',
  `verifytime` DATETIME DEFAULT NULL COMMENT '审核通过时间',
  `companyid` int(11) unsigned NOT NULL COMMENT '公司id',
  `userid` int(11) unsigned NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`),
  FOREIGN KEY(companyid) REFERENCES companys(id) on delete cascade on update cascade,
  FOREIGN KEY(userid) REFERENCES users(id) on delete cascade on update cascade
) ENGINE=InnoDB DEFAULT CHARSET=utf8
/

--//@UNDO
-- SQL to undo the change goes here.
