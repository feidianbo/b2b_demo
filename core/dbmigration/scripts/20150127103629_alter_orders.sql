--// alter_orders
-- Migration SQL that makes the change goes here.

ALTER TABLE orderverify modify status VARCHAR(20) DEFAULT NULL COMMENT '订单状态'
/

UPDATE orderverify SET status='WaitVerify' where status='待审核'
/
UPDATE orderverify SET status='VerifyPass' where status='审核通过'
/
UPDATE orderverify SET status='VerifyNotPass' where status='审核未通过'
/

--//@UNDO
-- SQL to undo the change goes here.


