--// alter_manualsell
-- Migration SQL that makes the change goes here.

alter table manualsell modify contactName varchar(20)   NOT NULL
/
alter table manualsell modify phone varchar(20) NOT NULL
/

--//@UNDO
-- SQL to undo the change goes here.


