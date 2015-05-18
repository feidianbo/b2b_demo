--// alter_companies
-- Migration SQL that makes the change goes here.

ALTER TABLE companies ADD openinglicense varchar(255) NOT NULL DEFAULT '' COMMENT '开户许可证'
/

--//@UNDO
-- SQL to undo the change goes here.


