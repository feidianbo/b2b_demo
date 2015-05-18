--// alter_sellinfo
-- Migration SQL that makes the change goes here.

ALTER TABLE sellinfo modify `soldquantity` decimal(10,0) DEFAULT '0'
/
UPDATE sellinfo set soldquantity='0' where soldquantity IS NULL
/

--//@UNDO
-- SQL to undo the change goes here.


