--// alter_articles
-- Migration SQL that makes the change goes here.

ALTER TABLE articles modify content text DEFAULT NULL COMMENT '内容'
/

--//@UNDO
-- SQL to undo the change goes here.


