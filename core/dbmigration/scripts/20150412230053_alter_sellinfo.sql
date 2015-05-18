--// alter_sellinfo
-- Migration SQL that makes the change goes here.

ALTER TABLE sellinfo ADD linktype tinyint(1) NOT NULL DEFAULT '0' COMMENT '联系方式，默认是0， 委托易煤网交易员寻找买家， 为1时，是联系客户提供的联系人'
/
ALTER TABLE sellinfo ADD linkmanname VARCHAR(30) DEFAULT NULL COMMENT '客户联系人姓名'
/
ALTER TABLE sellinfo add linkmanphone VARCHAR(20) DEFAULT NULL COMMENT '客户联系人电话'
/

--//@UNDO
-- SQL to undo the change goes here.


