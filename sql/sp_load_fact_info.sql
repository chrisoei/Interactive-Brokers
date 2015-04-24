CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_load_fact_info`()
begin

declare price_id INT DEFAULT 0;

INSERT INTO `bt_ib`.`fact_contract_info` (`data_key` ,`priceID` ,`conid` ,`localSymbol` ,`DateC` ,`timeC` ,`openC` ,`highC` ,`low` ,`closeC` ,`volume` ,`wap`)
select DISTINCT concat(conid," ", DateC," ",timeC) as data_key,priceID,conid,localSymbol,DateC,timeC,openC,highC,low,closeC,volume,wap
from tmp_contract_info;

truncate tmp_contract_info;

SELECT (MAX(priceid)+1) FROM fact_contract_info into @price_id;

end