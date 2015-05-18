--// alter_orders
-- Migration SQL that makes the change goes here.

alter table orders modify `pid` varchar(30) NOT NULL COMMENT '产品id'
/
alter table orders modify `orderid` varchar(30) DEFAULT NULL COMMENT 'Order Id'
/
alter table orders modify `remarks` varchar(100) DEFAULT NULL COMMENT '备注'
/

--//@UNDO
-- SQL to undo the change goes here.


