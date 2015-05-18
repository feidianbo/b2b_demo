--// alter_compversus
-- Migration SQL that makes the change goes here.

alter table compversus add backuptime TIMESTAMP NOT NULL COMMENT '备份时间/最后一次更改时间'
/

--//@UNDO
-- SQL to undo the change goes here.


