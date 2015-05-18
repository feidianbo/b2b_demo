--// alter_manuasell
-- Migration SQL that makes the change goes here.

alter table manualsell ADD `manualsellid` varchar(20) DEFAULT NULL COMMENT 'manualsell Id'
/

--//@UNDO
-- SQL to undo the change goes here.


