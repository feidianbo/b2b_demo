--// alter_sellinfo
-- Migration SQL that makes the change goes here.

ALTER TABLE sellinfo modify jtjlast int(4) DEFAULT '0' COMMENT '阶梯价，最低价'
/
UPDATE sellinfo SET jtjlast=0 WHERE jtjlast IS NULL
/

--//@UNDO
-- SQL to undo the change goes here.


