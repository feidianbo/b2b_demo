--// init_dealers
-- Migration SQL that makes the change goes here.

INSERT INTO `dealers` (`id`, `deliveryregion`, `deliveryprovince`, `deliveryplace`, `dealerid`, `dealername`, `createtime`, `lastupdatetime`, `lastupdatemanid`, `lastupdatemanname`, `uuid`, `dealerphone`)
VALUES
	(1, '易煤网', '易煤网', '易煤网', 'JYY000', '易煤网', '2015-01-16 09:47:11', '2015-01-22 15:05:08', 0, 'admin', NULL, '021-88888888')
/


--//@UNDO
-- SQL to undo the change goes here.


