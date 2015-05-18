--// alter_menus
-- Migration SQL that makes the change goes here.

ALTER TABLE menus ADD parentid int(11) unsigned NOT NULL
/
ALTER TABLE menus add sequence INT DEFAULT '100' COMMENT '次序'
/

--//@UNDO
-- SQL to undo the change goes here.


