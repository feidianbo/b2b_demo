--// init_menus
-- Migration SQL that makes the change goes here.

DELETE FROM menus
/
INSERT INTO `menus` (`id`, `menuname`, `url`, `parentid`, `sequence`)
VALUES
(1, '客户管理', '/#/user', 0, 1),
(2, '商城产品', '/#/mall/product', 0, 2),
(3, '前台资讯', '/#/article', 0, 3),
(4, '内部资讯', '/#/article', 0, 4),
(5, '供应管理', '/#/supply', 0, 5),
(6, '需求管理', '/#/demand', 0, 6),
(7, '财务管理', '/#/finance', 0, 7),
(8, '撮合交易', '/#/match/deal', 0, 8),
(9, '我的客户', '/#/myclient', 0, 9),
(10, '客户检索', '/#/customer/customersearch', 0, 10),
(11, '交易员管理', '/#/dealer', 0, 11),
(12, '商城订单', '/#/mall/order', 0, 12),
(13, '撮合订单', '/#/match/order', 0, 13),
(14, '金融客户', '/#/finance', 0, 14),
(15, '定时任务', '/#/timeTask', 0, 15),
(16, '团购供应商', '/#/supplier', 0, 16),
(17, '图表管理', '/#/chart', 0, 17),
(18, '系统配置', '/#/menu', 0, 18),
(19, '待审核', '/#/user/wait', 1, 1),
(20, '审核通过', '/#/user/pass', 1, 2),
(21, '审核未通过', '/#/user/fail', 1, 3),
(22, '待完善信息', '/#/user/info', 1, 4),

(23, '销售中', '/#/mall/sales', 2, 1),
(24, '已下架', '/#/mall/pulls', 2, 2),

(25, '待审核', '/#/supply/wait', 5, 1),
(26, '审核通过', '/#/supply/pass', 5, 2),
(27, '审核未通过', '/#/supply/fail', 5, 3),

(28, '待审核', '/#/demand/wait', 6, 1),
(29, '审核通过', '/#/demand/pass', 6, 2),
(30, '审核未通过', '/#/demand/fail', 6, 3),

(31, '待审核（订单）', '/#/certificate/wait', 7, 1),
(32, '待付尾款（订单）', '/#/certificate/tail', 7, 2),
(33, '审核通过（订单）', '/#/certificate/pass', 7, 3),
(34, '审核未通过（订单）', '/#/certificate/fail', 7, 4),
(35, '待审核（团购资质）', '/#/groupbuy/wait', 7, 5),
(36, '审核通过（团购资质）', '/#/groupbuy/pass', 7, 6),
(37, '审核未通过（团购资质）', '/#/groupbuy/fail', 7, 7),

(38, '供应信息', '/#/customer/bringdeal_sellinfo', 8, 1),
(39, '需求信息', '/#/customer/bringdeal_demand', 8, 1),

(40, '客户信息', '/#/customer/customerlist', 9, 1),
(41, '订单统计', '/#/customer/orderlist', 9, 2),
(42, '需求统计', '/#/customer/demandlist', 9, 3),
(43, '供应统计', '/#/customer/supplylist', 9, 4),
(44, '报价统计', '/#/customer/quotelist', 9, 5),
(45, '委托人工找货', '/#/customer/manualsellIn', 9, 6),
(46, '委托人工销售', '/#/customer/manualsellout', 9, 7),
(47, '团购资质', '/#/customer/teamorderqualification', 9, 8),
(48, '团购订单', '/#/customer/teamorder', 9, 9),

(49, '进行中', '/#/mall/underway', 12, 1),
(50, '退货中', '/#/mall/returning', 12, 2),
(51, '已完成', '/#/mall/finished', 12, 3),
(52, '已取消', '/#/mall/canceled', 12, 4),
(53, '待复审', '/#/mall/recheck', 12, 5),

(54, '进行中', '/#/order/recheck', 13, 5),
(55, '已完成', '/#/order/recheck', 13, 5),
(56, '已取消', '/#/order/recheck', 13, 5),
(57, '待复审', '/#/order/recheck', 13, 5),

(58, 'BSPI', '/#/chart/BSPI', 17, 1),
(59, 'API8', '/#/chart/API8', 17, 2),
(60, 'TC1505', '/#/chart/TC1505', 17, 3),

(61, '菜单管理', '/#/menu', 18, 1),
(62, '操作权限', '/#/operateauth', 18, 2),
(63, '添加用户', '/#/system/addUser', 18, 3),
(64, '添加角色', '/#/system/addRole', 18, 4)
/


--//@UNDO
-- SQL to undo the change goes here.


