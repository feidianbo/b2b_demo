--// alter_orders
-- Migration SQL that makes the change goes here.

ALTER TABLE orders change sellerdelete sellerstatus VARCHAR(20) DEFAULT 'NULL' COMMENT '订单状态(卖家)'
/

--//@UNDO
-- SQL to undo the change goes here.


