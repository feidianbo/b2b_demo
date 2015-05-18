--// alter_users
-- Migration SQL that makes the change goes here.

alter table users modify verifystatus VARCHAR (10) NOT NULL DEFAULT '待完善信息' COMMENT '用户公司信息状态'
/

--//@UNDO
-- SQL to undo the change goes here.


