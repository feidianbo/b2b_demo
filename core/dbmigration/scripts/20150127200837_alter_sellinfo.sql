--// alter_sellinfo
-- Migration SQL that makes the change goes here.

ALTER TABLE sellinfo ADD producttype VARCHAR(20) DEFAULT 'Common' COMMENT '产品类型，例如：推荐等'
/

--//@UNDO
-- SQL to undo the change goes here.


