--// update_sellinfo_parentid
-- Migration SQL that makes the change goes here.

ALTER TABLE sellinfo ADD  parentid  int(11) unsigned  DEFAULT '0'   COMMENT '修改原记录id'  /

ALTER TABLE sellinfo ADD  editnum  int(11) unsigned NOT NULL    COMMENT '次序'  /

ALTER TABLE sellinfo ADD  version  int(11)  NOT NULL  DEFAULT '0'   COMMENT '数据版本号，控制并发'  /

update    sellinfo  t set t.parentid = t.id where  t.parentid =0  and  t.editnum = 0  /

--//@UNDO
-- SQL to undo the change goes here.


