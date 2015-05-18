--// alter_menus
-- Migration SQL that makes the change goes here.

UPDATE menus set url='/#/order/underway' where id=54
/
UPDATE menus set url='/#/order/finished' where id=55
/
UPDATE menus set url='/#/order/canceled' where id=56
/

--//@UNDO
-- SQL to undo the change goes here.


