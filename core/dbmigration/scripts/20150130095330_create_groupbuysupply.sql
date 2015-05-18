--// create_groupbuysupply
-- Migration SQL that makes the change goes here.
alter table groupbuysupply add `comment` varchar(400) DEFAULT '' COMMENT '团购规则'
/

--//@UNDO
-- SQL to undo the change goes here.


