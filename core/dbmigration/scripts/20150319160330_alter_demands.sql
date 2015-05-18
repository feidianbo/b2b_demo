--// alter_demands
-- Migration SQL that makes the change goes here.

alter table demands  modify `ADS` decimal(5,2) DEFAULT NULL COMMENT '空干基硫份'
/

alter table demands  modify `RS` decimal(5,2) NOT NULL COMMENT '收到基硫份'
/

alter table demands  modify `TM` decimal(5,2) NOT NULL COMMENT '全水份'
/

alter table demands  modify `IM` decimal(5,2) DEFAULT NULL COMMENT '内水份'
/

alter table demands  modify `ADV` decimal(5,2) NOT NULL COMMENT '空干基挥发份'
/

alter table demands  modify `RV` decimal(5,2) DEFAULT NULL COMMENT '收到基挥发份'
/

--//@UNDO
-- SQL to undo the change goes here.


