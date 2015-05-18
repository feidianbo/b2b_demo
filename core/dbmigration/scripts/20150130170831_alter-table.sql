--// alter-table
-- Migration SQL that makes the change goes here.
alter table groupbuysupply add `deliverydatestart` date DEFAULT NULL COMMENT '提货时间开始'
/
alter table groupbuysupply add `deliverydateend` date DEFAULT NULL COMMENT '提货时间截至'
/
alter table groupbuysupply add `otherorg` varchar(30) DEFAULT '' COMMENT '其他机构'
/
--//@UNDO
-- SQL to undo the change goes here.


