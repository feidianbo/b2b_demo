--// alter_orders
-- Migration SQL that makes the change goes here.

ALTER TABLE orders modify status VARCHAR(20) DEFAULT NULL COMMENT '订单状态'
/
ALTER TABLE orders modify paytype VARCHAR(20) DEFAULT NULL COMMENT '付款方式，分为：付全款，付保证金'
/

--//@UNDO
-- SQL to undo the change goes here.


