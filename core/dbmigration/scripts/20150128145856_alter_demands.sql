--// alter_demands
-- Migration SQL that makes the change goes here.

alter table demands drop column traderid
/
ALTER TABLE demands ADD tradercode varchar(10) DEFAULT NULL COMMENT '交易员编号'
/
--//@UNDO
-- SQL to undo the change goes here.


