--// alter_myinterest
-- Migration SQL that makes the change goes here.

alter table myinterest add type VARCHAR (10) DEFAULT NULL COMMENT '关注类型'
/
alter table myinterest change sellinfoid sid int(11) unsigned NOT NULL COMMENT 'sellinfo表对应的id，或者demand表对应的id'
/
alter table myinterest modify lastupdatetime TIMESTAMP COMMENT '最后一次更新时间'
/

--//@UNDO
-- SQL to undo the change goes here.


