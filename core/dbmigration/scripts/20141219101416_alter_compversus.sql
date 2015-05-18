--// alter_compversus
-- Migration SQL that makes the change goes here.

alter table compversus modify column legalperson VARCHAR(255) DEFAULT NULL COMMENT '公司法人'
/

--//@UNDO
-- SQL to undo the change goes here.


