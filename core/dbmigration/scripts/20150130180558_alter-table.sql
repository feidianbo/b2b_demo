--// alter-table
-- Migration SQL that makes the change goes here.
alter table groupbuyorder add `performancecode` varchar(20) DEFAULT '' COMMENT '履约书合同编号'
/


--//@UNDO
-- SQL to undo the change goes here.


