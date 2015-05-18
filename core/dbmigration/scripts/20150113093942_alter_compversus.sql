--// alter_compversus
-- Migration SQL that makes the change goes here.

ALTER TABLE compversus ADD `account` varchar(30) NOT NULL DEFAULT '' COMMENT '银行账号'
/
ALTER TABLE compversus ADD `openingbank` varchar(20) NOT NULL DEFAULT '' COMMENT '开户银行'
/
ALTER TABLE compversus ADD `legalpersonname` varchar(20) NOT NULL DEFAULT '' COMMENT '法人姓名'
/
ALTER TABLE compversus ADD `traderphone` varchar(20) DEFAULT '' COMMENT '交易员手机'
/

--//@UNDO
-- SQL to undo the change goes here.


