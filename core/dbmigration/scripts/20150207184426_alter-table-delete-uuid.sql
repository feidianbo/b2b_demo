--// alter-table-delete-uuid
-- Migration SQL that makes the change goes here.

alter table dealers drop uuid
/
alter table orders drop uuid
/
alter table sellinfo drop uuid
/
alter table payment drop uuid
/

--//@UNDO
-- SQL to undo the change goes here.


