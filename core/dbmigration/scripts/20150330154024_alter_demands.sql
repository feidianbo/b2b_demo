--// alter_demands
-- Migration SQL that makes the change goes here.

ALTER TABLE demands add viewtimes int(11) unsigned DEFAULT '0' COMMENT '浏览次数'
/

--//@UNDO
-- SQL to undo the change goes here.


