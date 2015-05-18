--// init_net_writer
-- Migration SQL that makes the change goes here.

INSERT INTO `rolesmenus` (`id`, `roleid`, `menuid`)
VALUES
	(79, 3, 3)
	/

--//@UNDO
-- SQL to undo the change goes here.


