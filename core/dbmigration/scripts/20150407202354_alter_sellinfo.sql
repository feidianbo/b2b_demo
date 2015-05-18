--// alter_sellinfo
-- Migration SQL that makes the change goes here.

ALTER TABLE sellinfo modify `deliveryplace` VARCHAR(30) DEFAULT NULL COMMENT '港口'
/
ALTER TABLE sellinfo modify `deliveryregion` VARCHAR(30) DEFAULT NULL COMMENT '提货地区'
/
ALTER TABLE sellinfo modify `deliveryprovince` VARCHAR(30) DEFAULT NULL COMMENT '提货省市'
/

--//@UNDO
-- SQL to undo the change goes here.


