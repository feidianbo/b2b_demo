--// alter_compversus
-- Migration SQL that makes the change goes here.

alter table compversus modify `verifystatus` varchar(10) DEFAULT '审核通过' COMMENT '公司信息审核状态'
/

--//@UNDO
-- SQL to undo the change goes here.


