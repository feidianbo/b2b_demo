--// alter_companies
-- Migration SQL that makes the change goes here.

ALTER TABLE companies ADD identificationnumword varchar(30) NOT NULL DEFAULT '' COMMENT '纳税人识别号(文字)'
/
ALTER TABLE companies ADD zipcode varchar(10) NOT NULL DEFAULT '' COMMENT '邮编'
/

--//@UNDO
-- SQL to undo the change goes here.


