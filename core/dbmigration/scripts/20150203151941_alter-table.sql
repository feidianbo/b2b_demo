--// alter-table
-- Migration SQL that makes the change goes here.

alter table groupbuysupply modify `groupbuybegindate` datetime DEFAULT NULL COMMENT '团购开始时间'
/
alter table groupbuysupply modify `groupbuyenddate` date DEFAULT NULL COMMENT '团购结束时间'
/
alter table groupbuysupply add `photopath` varchar(200) DEFAULT '' COMMENT '团购发布图片路径'
/

--//@UNDO
-- SQL to undo the change goes here.


