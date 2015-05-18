--// create_dealerport
-- Migration SQL that makes the change goes here.
alter table demands add column traderid  int (30)  Default NULL/

alter table sellinfo add column traderid  int (30)  Default NULL/

alter table orders add column traderid int(30) Default NULL/




CREATE TABLE `dealerport` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `dealerId` int(11) NOT NULL,
  `portId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/


--//@UNDO
-- SQL to undo the change goes here.


