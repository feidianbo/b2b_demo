--// alter-table
-- Migration SQL that makes the change goes here.

ALTER TABLE groupbuyqualification modify `photopath` varchar(200) DEFAULT '' COMMENT '保证金图片路径'
/

--//@UNDO
-- SQL to undo the change goes here.


