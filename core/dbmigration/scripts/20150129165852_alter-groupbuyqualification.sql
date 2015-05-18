--// alter-groupbuyqualification
-- Migration SQL that makes the change goes here.

alter table groupbuyqualification add `comment` varchar(100) DEFAULT '' COMMENT '备注'
/

--//@UNDO
-- SQL to undo the change goes here.


