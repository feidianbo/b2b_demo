--// alter_dealerbackup
-- Migration SQL that makes the change goes here.

ALTER TABLE dealerbackup ADD dealerphone VARCHAR(15) NOT NULL COMMENT '交易员电话'
/

--//@UNDO
-- SQL to undo the change goes here.


