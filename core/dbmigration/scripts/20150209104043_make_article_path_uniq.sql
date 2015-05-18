--// make article path uniq
-- Migration SQL that makes the change goes here.

alter table articles add UNIQUE path (path)
/

--//@UNDO
-- SQL to undo the change goes here.


