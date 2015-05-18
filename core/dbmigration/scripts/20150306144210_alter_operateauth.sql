--// alter_operateauth
-- Migration SQL that makes the change goes here.

ALTER TABLE operateauth ADD  `menuid` int(11) NOT NULL
/

ALTER TABLE operateauth ADD  `menuname` varchar(10) NOT NULL DEFAULT '' COMMENT '菜单名称'
/

--//@UNDO
-- SQL to undo the change goes here.


