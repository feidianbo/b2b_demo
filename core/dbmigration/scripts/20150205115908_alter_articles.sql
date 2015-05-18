--// alter_articles
-- Migration SQL that makes the change goes here.

ALTER TABLE articles add sequence INT DEFAULT '100' COMMENT '次序'
/

--//@UNDO
-- SQL to undo the change goes here.


