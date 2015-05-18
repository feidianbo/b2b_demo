--// alter_manualsell
-- Migration SQL that makes the change goes here.

ALTER TABLE manualsell ADD  createdatetime date DEFAULT NULL
/

--//@UNDO
-- SQL to undo the change goes here.


