--// alter_orders
-- Migration SQL that makes the change goes here.

ALTER TABLE orderreturn modify status VARCHAR(20) DEFAULT 'ReturnGoods' COMMENT '订单状态'
/
ALTER TABLE orderreturn modify laststatus VARCHAR(20) DEFAULT NULL COMMENT '上一个状态'
/

UPDATE orderreturn SET status='ReturnGoods'
/

UPDATE orderreturn SET laststatus='WaitVerify' where laststatus='待审核'
/
UPDATE orderreturn SET laststatus='VerifyPass' where laststatus='审核通过'
/
UPDATE orderreturn SET laststatus='WaitBalancePayment' where laststatus='待付尾款'
/
UPDATE orderreturn SET laststatus='ReturnGoods' where laststatus='退货中'
/

--//@UNDO
-- SQL to undo the change goes here.


