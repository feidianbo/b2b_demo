--// alter_admins
-- Migration SQL that makes the change goes here.

ALTER TABLE admins modify jobnum varchar(30) COMMENT '工号'
/
ALTER TABLE admins modify role varchar(20) COMMENT '角色'
/
ALTER TABLE admins modify name varchar(20) COMMENT '姓名'
/

--//@UNDO
-- SQL to undo the change goes here.


