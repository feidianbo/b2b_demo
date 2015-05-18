--// alter-table
-- Migration SQL that makes the change goes here.
alter table groupbuysupply modify `groupbuyenddate` datetime DEFAULT NULL COMMENT '团购结束时间'
/


--//@UNDO
-- SQL to undo the change goes here.


