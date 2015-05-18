--// alter_dealers
-- Migration SQL that makes the change goes here.

ALTER TABLE dealers ADD status VARCHAR(10) DEFAULT '在职' COMMENT '工作状态'
/

--//@UNDO
-- SQL to undo the change goes here.


