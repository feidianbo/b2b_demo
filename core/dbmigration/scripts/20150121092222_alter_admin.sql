--// alter_admin
-- Migration SQL that makes the change goes here.

ALTER TABLE admins ADD jobnum VARCHAR(30) NOT NULL COMMENT '工号'
/
ALTER TABLE admins ADD role VARCHAR(20) NOT NULL COMMENT '角色'
/
ALTER TABLE admins ADD name VARCHAR(20) NOT NULL COMMENT '姓名'
/

--//@UNDO
-- SQL to undo the change goes here.


