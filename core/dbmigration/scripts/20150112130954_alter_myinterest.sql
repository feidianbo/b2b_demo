--// alter_myinterest
-- Migration SQL that makes the change goes here.

ALTER TABLE myinterest ADD amount int(6) unsigned DEFAULT NULL COMMENT '数量'
/
ALTER TABLE myinterest ADD NCV int(5) unsigned NOT NULL COMMENT '收到基低位发热量'
/

--//@UNDO
-- SQL to undo the change goes here.


