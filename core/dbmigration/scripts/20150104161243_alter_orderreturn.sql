--// alter_orderreturn
-- Migration SQL that makes the change goes here.

ALTER table orderreturn modify username VARCHAR(20) DEFAULT NULl COMMENT '申请人name'
/

--//@UNDO
-- SQL to undo the change goes here.


