--// alter_chartconfine
-- Migration SQL that makes the change goes here.

alter table chartconfine  modify `highlimit` decimal(5,1) NOT NULL DEFAULT '0.0' COMMENT '上限'
/

alter table chartconfine  modify `lowlimit` decimal(5,1) NOT NULL DEFAULT '0.0' COMMENT '下限'
/
--//@UNDO
-- SQL to undo the change goes here.


