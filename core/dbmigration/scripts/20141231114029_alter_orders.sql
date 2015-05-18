--// alter_orders
-- Migration SQL that makes the change goes here.

ALTER TABLE orders ADD paidmoney DECIMAL(20,2) DEFAULT NULL COMMENT '已付货款'
/
ALTER TABLE orders ADD waitmoney DECIMAL(20,2) DEFAULT NULL COMMENT '待付货款'
/
ALTER TABLE orders ADD savemoney DECIMAL(20,2) DEFAULT NULL COMMENT '节余款'
/

--//@UNDO
-- SQL to undo the change goes here.


