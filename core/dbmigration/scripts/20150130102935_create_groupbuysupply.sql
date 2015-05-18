--// create_groupbuysupply
-- Migration SQL that makes the change goes here.

 alter table groupbuysupply add `deliverydistrict` varchar(10)  DEFAULT '' COMMENT '提货省份片区'
 /
 alter table groupbuysupply add `deliveryprovince` varchar(10)  DEFAULT '' COMMENT '提货省份'
 /
 alter table groupbuysupply add `groupbuyordercount` int(4) DEFAULT NULL COMMENT '团购订单数量'
 /

--//@UNDO
-- SQL to undo the change goes here.


