--// alter_orders
-- Migration SQL that makes the change goes here.

ALTER TABLE orders ADD dealerid VARCHAR(30) DEFAULT NULL COMMENT '交易员编号'
/
ALTER TABLE orders ADD dealername VARCHAR(30) DEFAULT NULL COMMENT '交易员name'
/
ALTER TABLE orders ADD dealerphone VARCHAR(15) DEFAULT NULL COMMENT '交易员phone'
/
ALTER TABLE orders DROP COLUMN isdelete
/

--//@UNDO
-- SQL to undo the change goes here.


