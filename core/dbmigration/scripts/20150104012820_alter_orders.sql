--// alter_orders
-- Migration SQL that makes the change goes here.

ALTER TABLE orders modify paidmoney DECIMAL(20,2) DEFAULT '0' COMMENT '已付货款'
/
ALTER TABLE orders modify waitmoney DECIMAL(20,2) DEFAULT '0' COMMENT '待付货款'
/
ALTER TABLE orders modify savemoney DECIMAL(20,2) DEFAULT '0' COMMENT '节余款'
/

--//@UNDO
-- SQL to undo the change goes here.


