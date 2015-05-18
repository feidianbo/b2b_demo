--// alter_articles
-- Migration SQL that makes the change goes here.

ALTER TABLE articles modify path VARCHAR(255) DEFAULT NULL
/

--//@UNDO
-- SQL to undo the change goes here.


