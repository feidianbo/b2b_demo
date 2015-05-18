--// alter-table
-- Migration SQL that makes the change goes here.
alter table groupbuysupply drop column status
/
alter table groupbuysupply  add `status` varchar(50) DEFAULT '' COMMENT '状态'
/


--//@UNDO
-- SQL to undo the change goes here.


