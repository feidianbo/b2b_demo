--// alter_payment
-- Migration SQL that makes the change goes here.

ALTER TABLE payment ADD uuid varchar(50) DEFAULT NULL COMMENT 'UUID'
/

--//@UNDO
-- SQL to undo the change goes here.


