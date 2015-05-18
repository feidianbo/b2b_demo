--// alter_payment
-- Migration SQL that makes the change goes here.

ALTER TABLE payment drop COLUMN squence
/
ALTER TABLE payment modify isverified tinyint(1) NOT NULL DEFAULT '0' COMMENT '此凭证是否已经验证'
/
ALTER TABLE payment modify username varchar(20) DEFAULT NULL COMMENT '上传人name'
/

--//@UNDO
-- SQL to undo the change goes here.


