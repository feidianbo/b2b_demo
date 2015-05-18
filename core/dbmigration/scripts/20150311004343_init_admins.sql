--// init_admins
-- Migration SQL that makes the change goes here.

INSERT INTO `rolesmenus` (`id`, `roleid`, `menuid`)
VALUES
	(160, 7, 11),

	(161, 7, 18),
	(162, 7, 61),
	(163, 7, 62),
	(164, 7, 63),
	(165, 7, 64)
/

--//@UNDO
-- SQL to undo the change goes here.


