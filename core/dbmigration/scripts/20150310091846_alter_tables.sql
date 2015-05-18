--// alter_tables
-- Migration SQL that makes the change goes here.

alter table menus drop column menuname
/

ALTER TABLE menus ADD `menuname` varchar(30) NOT NULL DEFAULT ''
/

alter table operateauth drop column menuname
/

ALTER TABLE operateauth ADD `menuname` varchar(30) NOT NULL DEFAULT '' COMMENT '菜单名称'
/

--//@UNDO
-- SQL to undo the change goes here.


