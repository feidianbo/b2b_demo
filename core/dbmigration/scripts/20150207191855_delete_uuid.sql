--// delete_uuid
-- Migration SQL that makes the change goes here.

ALTER TABLE articles DROP COLUMN uuid
/
ALTER TABLE orders DROP COLUMN performancecode
/

--//@UNDO
-- SQL to undo the change goes here.


