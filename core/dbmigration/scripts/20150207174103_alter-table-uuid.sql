--// alter-table-uuid
-- Migration SQL that makes the change goes here.
alter table groupbuyorder drop uuid
/
alter table groupbuyqualification drop uuid
/
alter table groupbuysupply drop uuid
/
alter table providerinfo drop uuid
/
--//@UNDO
-- SQL to undo the change goes here.


