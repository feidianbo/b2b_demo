--// alter_orders
-- Migration SQL that makes the change goes here.

alter table orders add paytype VARCHAR(10) DEFAULT NULL COMMENT '付款方式，分为：付全款，付保证金'
/
alter table orders add isfutures TINYINT(1) NOT NULL DEFAULT '0' COMMENT '是否为期货'
/
alter table orders add contractno VARCHAR(30) DEFAULT NULL COMMENT '合同编号'
/

--//@UNDO
-- SQL to undo the change goes here.


