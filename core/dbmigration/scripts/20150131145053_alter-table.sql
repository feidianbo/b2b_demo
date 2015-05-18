--// alter-table
-- Migration SQL that makes the change goes here.
alter table groupbuysupply drop column groupbuybegindate
/
alter table groupbuysupply drop column groupbuyenddate
/
alter table groupbuysupply add `groupbuybegindate` date DEFAULT NULL COMMENT '团购开始时间'
/
alter table groupbuysupply add `groupbuyenddate` date DEFAULT NULL COMMENT '团购结束时间'
/
--//@UNDO
-- SQL to undo the change goes here.


