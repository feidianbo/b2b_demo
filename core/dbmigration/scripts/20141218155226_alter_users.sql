--// alter_users
-- Migration SQL that makes the change goes here.

alter table users add verifystatus varchar(10) DEFAULT NULL COMMENT '公司信息审核状态'
/

--//@UNDO
-- SQL to undo the change goes here.


