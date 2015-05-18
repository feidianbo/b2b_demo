--// alter admins
-- Migration SQL that makes the change goes here.

ALTER TABLE admins ADD  phone varchar(20) DEFAULT '' COMMENT '交易员手机号'
/

--//@UNDO
-- SQL to undo the change goes here.