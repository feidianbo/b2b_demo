--// alter-table
-- Migration SQL that makes the change goes here.

alter table groupbuyorder add  `groupbuysupplycode` varchar(20) NOT NULL COMMENT '团购供货信息编号'
/
alter table groupbuysupply add  `groupbuysupplycode` varchar(20) NOT NULL COMMENT '团购供货信息编号'
/
--//@UNDO
-- SQL to undo the change goes here.


