--// alter_sellinfo
-- Migration SQL that makes the change goes here.

alter table sellinfo  modify `paymode` int(11) DEFAULT NULL COMMENT '付款方式'
/

--//@UNDO
-- SQL to undo the change goes here.


