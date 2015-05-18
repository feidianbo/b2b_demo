--// alter_articles
-- Migration SQL that makes the change goes here.

ALTER TABLE articles DROP COLUMN typedetail
/
ALTER TABLE articles DROP COLUMN type
/
ALTER TABLE articles ADD uuid VARCHAR(50) DEFAULT NULL COMMENT 'uuid'
/

--//@UNDO
-- SQL to undo the change goes here.


