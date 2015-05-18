--// alter_groupbuysupply
-- Migration SQL that makes the change goes here.

ALTER TABLE groupbuysupply modify NCV int(5) unsigned NOT NULL DEFAULT '0' COMMENT '收到基低位发热量'
/
ALTER TABLE groupbuysupply modify ADS decimal(3,1) DEFAULT '0' COMMENT '空气干燥基全硫'
/
ALTER TABLE groupbuysupply modify RS decimal(3,1) NOT NULL DEFAULT '0' COMMENT '收到基全硫'
/
ALTER TABLE groupbuysupply modify TM decimal(4,1) NOT NULL DEFAULT '0' COMMENT '全水 0-100'
/
ALTER TABLE groupbuysupply modify IM decimal(4,1) DEFAULT '0' COMMENT '内水 0-100'
/
ALTER TABLE groupbuysupply modify ADV decimal(4,1) NOT '0' COMMENT '空气干燥基挥发份'
/
ALTER TABLE groupbuysupply modify RV decimal(4,1) DEFAULT '0' COMMENT '收到基挥发份'
/
ALTER TABLE groupbuysupply modify ASH decimal(4,1) DEFAULT '0' COMMENT '收到基灰分'
/
ALTER TABLE groupbuysupply modify AFT int(4) unsigned DEFAULT '0' COMMENT '灰熔点，软化温度'
/
ALTER TABLE groupbuysupply modify HGI int(4) DEFAULT '0' COMMENT '哈氏可磨'
/

--//@UNDO
-- SQL to undo the change goes here.


