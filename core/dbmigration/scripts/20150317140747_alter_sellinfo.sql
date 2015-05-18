--// alter_sellinfo
-- Migration SQL that makes the change goes here.

ALTER TABLE sellinfo modify `ADS` decimal(4,2) DEFAULT NULL COMMENT '空气干燥基全硫'
/
ALTER TABLE sellinfo modify `RS` decimal(4,2) NOT NULL COMMENT '收到基全硫'
/
ALTER TABLE sellinfo modify `TM` decimal(5,2) NOT NULL COMMENT '全水 0-100'
/
ALTER TABLE sellinfo modify `IM` decimal(5,2) DEFAULT NULL COMMENT '内水 0-100'
/
ALTER TABLE sellinfo modify `ADV` decimal(5,2) NOT NULL COMMENT '空气干燥基挥发份'
/
ALTER TABLE sellinfo modify `RV` decimal(5,2) DEFAULT NULL COMMENT '收到基挥发份'
/


--//@UNDO
-- SQL to undo the change goes here.


