--// alter_companies
-- Migration SQL that makes the change goes here.

ALTER TABLE companies modify remarks  varchar(200) DEFAULT NULL COMMENT '备注'
/
ALTER TABLE compverify modify remarks VARCHAR(200) DEFAULT NULL COMMENT '备注'
/

--//@UNDO
-- SQL to undo the change goes here.


