--// alter_dictionaries
-- Migration SQL that makes the change goes here.

UPDATE dictionaries SET name='到岸舱底' where name='到岸仓底'
/

--//@UNDO
-- SQL to undo the change goes here.


