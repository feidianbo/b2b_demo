--// alter_orders
-- Migration SQL that makes the change goes here.

alter table orders add paydocument varchar(255) DEFAULT NULL COMMENT '支付凭证图片'
/
alter table orders add remarks varchar(50) DEFAULT NULL COMMENT '备注'
/

--//@UNDO
-- SQL to undo the change goes here.


