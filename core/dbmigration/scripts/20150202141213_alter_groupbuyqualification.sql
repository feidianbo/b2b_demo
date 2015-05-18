--// alter_groupbuyqualification
-- Migration SQL that makes the change goes here.

ALTER TABLE groupbuyqualification modify margins decimal(20,2) DEFAULT '0' COMMENT '保证金'
/

--//@UNDO
-- SQL to undo the change goes here.


