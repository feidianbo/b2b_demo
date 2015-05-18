--// alter_companies
-- Migration SQL that makes the change goes here.

ALTER TABLE companies ADD invoicinginformation varchar(255) DEFAULT NULL COMMENT '企业开票信息'
/
ALTER TABLE companies modify legalperson varchar(255) DEFAULT NULL COMMENT '公司法人身份证'
/

--//@UNDO
-- SQL to undo the change goes here.


