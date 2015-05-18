--// alter_orders
-- Migration SQL that makes the change goes here.

ALTER TABLE ordersinfo modify status VARCHAR(20) DEFAULT NULL COMMENT '订单状态'
/
ALTER TABLE orderverify modify status VARCHAR(20) DEFAULT NULL COMMENR '订单状态'
/

--//@UNDO
-- SQL to undo the change goes here.


