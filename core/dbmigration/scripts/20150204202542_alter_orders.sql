--// alter_orders
-- Migration SQL that makes the change goes here.

UPDATE orders set sellerstatus='NULL'
/

--//@UNDO
-- SQL to undo the change goes here.


