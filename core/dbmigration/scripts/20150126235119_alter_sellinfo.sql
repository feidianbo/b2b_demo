--// alter_sellinfo
-- Migration SQL that makes the change goes here.

ALTER TABLE supplyverify modify status VARCHAR(20) DEFAULT NULL COMMENT '供应信息状态'
/

update supplyverify set status='VerifyPass' where status='审核通过'
/
update supplyverify set status='WaitVerify' where status='待审核'
/
update supplyverify set status='VerifyNotPass' where status='审核未通过'
/

--//@UNDO
-- SQL to undo the change goes here.


