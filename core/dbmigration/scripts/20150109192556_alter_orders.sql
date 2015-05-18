--// alter_orders
-- Migration SQL that makes the change goes here.

ALTER TABLE orders ADD sellerid int(11) unsigned NOT NULL COMMENT '卖家id'
/
ALTER TABLE orders ADD sellerdelete tinyint(1) DEFAULT '0' COMMENT '卖家是否删除订单'
/
ALTER TABLE orders ADD deliveryregion varchar(30) NOT NULL DEFAULT '' COMMENT '提货地区'
/
ALTER TABLE orders ADD deliveryprovince varchar(30) NOT NULL DEFAULT '' COMMENT '提货省市'
/
ALTER TABLE orders ADD otherharbour varchar(20) DEFAULT NULL COMMENT '其它港口'
/

--//@UNDO
-- SQL to undo the change goes here.


