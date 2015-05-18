--// alter_addColumn_orders_sellinfo_demands_manualsell
-- Migration SQL that makes the change goes here.


-- alter orders table

ALTER TABLE orders ADD  regionId INT(30) NOT NULL /

ALTER TABLE orders ADD  provinceId INT(30) NOT NULL /

ALTER TABLE orders ADD  portId INT(30) NOT NULL /

update orders o , areaports area set o.regionId = area.id where o.deliveryregion=area.name and area.code='area'/

update orders o , areaports area set o.provinceId = area.id where o.deliveryprovince = area.name and area.code='province'/

update orders o , areaports area set o.portId = area.id where o.deliveryplace = area.name and area.code='port' /


--alter sellinfo table
ALTER TABLE sellinfo ADD regionId INT(30) NOT NULL /

ALTER TABLE sellinfo ADD provinceId INT(30) NOT NULL /

ALTER TABLE sellinfo ADD portId INT(30) NOT NULL /

update sellinfo s , areaports area set s.regionId = area.id where s.deliveryregion=area.name and area.code='area' /

update sellinfo s , areaports area set s.provinceId = area.id where s.deliveryprovince = area.name and area.code='province' /

update sellinfo s , areaports area set s.portId = area.id where s.deliveryplace = area.name and area.code='port' /


--alter demands table

ALTER TABLE demands ADD  regionId INT(30) NOT NULL /

ALTER TABLE demands ADD  provinceId INT(30) NOT NULL /

ALTER TABLE demands ADD  portId INT(30) NOT NULL /

update demands d , areaports area set d.regionId = area.id where d.deliverydistrict=area.name and area.code='area' /

update demands d , areaports area set d.provinceId = area.id where d.deliveryprovince = area.name and area.code='province' /

update demands d , areaports area set d.portId = area.id where d.deliveryplace = area.name and area.code='port' /



