--// alter_orders
-- Migration SQL that makes the change goes here.

UPDATE orders SET status='WaitConfirmed' WHERE status='待确认'
/
UPDATE orders SET status='WaitSignContract' WHERE status='待签合同'
/
UPDATE orders SET status='WaitPayment' WHERE status='待付款'
/
UPDATE orders SET status='WaitVerify' where status='待审核'
/
UPDATE orders SET status='VerifyPass' where status='审核通过'
/
UPDATE orders SET status='VerifyNotPass' where status='审核未通过'
/
UPDATE orders SET status='WaitBalancePayment' where status='待付尾款'
/
UPDATE orders SET status='ReturnGoods' where status='退货中'
/
UPDATE orders SET status='Canceled' where status='已取消'
/
UPDATE orders SET status='Deleted' where status='已删除'
/
UPDATE orders SET status='Completed' where status='已完成'
/
UPDATE orders SET status='ReturnCompleted' where status='退货完成'
/
UPDATE orders SET status='MakeMatch' where status='撮合中'
/

UPDATE orders SET paytype='PayTheWhole' where paytype='付全款'
/
UPDATE orders SET paytype='PayCashDeposit' where paytype!='付全款'
/

UPDATE orders SET ordertype='MallOrder' where ordertype='自营单'
/
UPDATE orders SET ordertype='OtherOrder' where ordertype='委托单'
/

--//@UNDO
-- SQL to undo the change goes here.


