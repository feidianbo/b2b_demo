--// alter-tbale
-- Migration SQL that makes the change goes here.

 alter table groupbuyqualification add `marginscode` varchar(20)  DEFAULT '' COMMENT '保证金合同编号'
 /
 alter table orders add `performancecode` varchar(20)  DEFAULT '' COMMENT '履约书合同编号'
 /


--//@UNDO
-- SQL to undo the change goes here.


