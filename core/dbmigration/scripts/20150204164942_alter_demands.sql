--// alter_demands
-- Migration SQL that makes the change goes here.

ALTER TABLE demands ADD tradername varchar(10) NOT NULL DEFAULT '' COMMENT '交易员姓名'
/
ALTER TABLE demands ADD traderphone varchar(20) NOT NULL DEFAULT '' COMMENT '交易员手机号'
/

--//@UNDO
-- SQL to undo the change goes here.


