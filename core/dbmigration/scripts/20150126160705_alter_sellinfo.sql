--// alter_sellinfo
-- Migration SQL that makes the change goes here.

ALTER TABLE sellinfo modify status VARCHAR(20) DEFAULT NULL COMMENT '供应信息状态'
/

update sellinfo set status='VerifyPass' where status='审核通过'
/
update sellinfo set status='Deleted' where status='已删除'
/
update sellinfo set status='WaitConfirmed' where status='待确认'
/
update sellinfo set status='WaitVerify' where status='待审核'
/
update sellinfo set status='Canceled' where status='已取消'
/
update sellinfo set status='VerifyNotPass' where status='审核未通过'
/
update sellinfo set status='OutOfDate' where status='已过期'
/
update sellinfo set status='OutOfStack' where status='已下架'
/

--//@UNDO
-- SQL to undo the change goes here.


