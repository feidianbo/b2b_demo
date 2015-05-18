--// alter_finance
-- Migration SQL that makes the change goes here.

ALTER TABLE finance ADD amountnum int(11) NOT NULL DEFAULT '0' COMMENT '融资金额(整数)'
/

--//@UNDO
-- SQL to undo the change goes here.


