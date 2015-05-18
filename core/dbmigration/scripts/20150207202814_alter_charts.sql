--// alter_charts
-- Migration SQL that makes the change goes here.

alter table charts drop column averageprice
/
ALTER TABLE charts ADD averageprice decimal(6,2) NOT NULL COMMENT '平均价格'
/

--//@UNDO
-- SQL to undo the change goes here.


