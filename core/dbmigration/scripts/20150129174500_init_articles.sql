--// init_articles
-- Migration SQL that makes the change goes here.

INSERT INTO `articles` (`id`, `title`, `summary`, `keywords`, `content`, `author`, `source`, `createtime`, `lastupdatetime`, `isdelete`, `parentid`, `haschild`, `path`, `aid`, `lastupdateman`, `uuid`)
VALUES
	(100, '区域行情', '区域行情', '区域行情', '易煤网区域行情', '刘新杰', '易煤网', '2015-01-29 10:54:46', '2015-01-29 11:07:48', 0, 0, 0, '区域行情', 'A201501294', 'admin', '5d30c699-e1a8-450c-a676-e85e99998b97'),
	(101, '石油', '石油', '石油', '易煤网石油新闻', '刘新杰', '易煤网', '2015-01-29 10:55:07', '2015-01-29 11:07:56', 0, 0, 0, '石油', 'A201501295', 'admin', '356f14cf-2378-45d8-81bd-615201ddad0d'),
	(102, '煤炭', '煤炭', '煤炭', '易煤网煤炭新闻', '刘新杰', '易煤网', '2015-01-29 10:55:33', '2015-01-29 11:08:29', 0, 0, 0, '煤炭', 'A201501296', 'admin', 'a2b69483-bf1a-44ae-91ca-120da0a48a4c'),
	(103, '有色', '有色金属', '有色', '易煤网有色金属新闻', '刘新杰', '易煤网', '2015-01-29 10:55:58', '2015-01-29 11:08:35', 0, 0, 0, '有色', 'A201501297', 'admin', 'b4d3ad94-8961-4bcd-ab1d-c1f77948201f')
	/

--//@UNDO
-- SQL to undo the change goes here.


