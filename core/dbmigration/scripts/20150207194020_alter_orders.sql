--// alter_orders
-- Migration SQL that makes the change goes here.

ALTER TABLE orders DROP COLUMN paydocument
/
ALTER TABLE orders DROP COLUMN transportmode
/
ALTER TABLE orders ADD ischange tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已经修改'
/

--//@UNDO
-- SQL to undo the change goes here.


