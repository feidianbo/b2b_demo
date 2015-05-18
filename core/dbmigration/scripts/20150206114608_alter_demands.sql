--// alter_demands
-- Migration SQL that makes the change goes here.

alter table demands drop column inspectionagency
/

alter table demands drop column otherplace
/

alter table demands drop column otherorg
/

ALTER TABLE demands ADD inspectionagency varchar(50) NOT NULL DEFAULT '' COMMENT '检验机构'
/

ALTER TABLE demands ADD otherplace varchar(50) DEFAULT NULL COMMENT '其它提货地点'
/

ALTER TABLE demands ADD otherorg varchar(50) DEFAULT NULL COMMENT '其它检验机构'
/

--//@UNDO
-- SQL to undo the change goes here.


