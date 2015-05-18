--// alter_article
-- Migration SQL that makes the change goes here.

alter table articles add lastupdateman VARCHAR (20) DEFAULT NULL COMMENT '最后一次更新的操作人'
/
alter table articles modify lastupdatetime TIMESTAMP COMMENT '最后一次更新时间'
/

--//@UNDO
-- SQL to undo the change goes here.


