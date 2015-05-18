--// alter_sellinfoAndDeamnd
-- Migration SQL that makes the change goes here.
alter table sellinfo add `originplace` varchar(100) NOT NULL DEFAULT '' COMMENT '产地'
/

alter table sellinfo add `paymode` int(11) NOT NULL COMMENT '付款方式'
/

alter table sellinfo add `payperiod` decimal(3,1) DEFAULT NULL COMMENT '到账周期'
/

alter table sellinfo add `releaseremarks` varchar(200) DEFAULT '' COMMENT '发布备注'
/

alter table demands add `releasecomment` varchar(200) DEFAULT NULL COMMENT '发布备注'
/


--//@UNDO
-- SQL to undo the change goes here.


