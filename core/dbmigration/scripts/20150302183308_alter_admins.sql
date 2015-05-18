--// alter_admins
-- Migration SQL that makes the change goes here.

ALTER TABLE admins modify username VARCHAR(30) NOT NULL DEFAULT ''
/

--//@UNDO
-- SQL to undo the change goes here.


