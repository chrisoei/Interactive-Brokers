CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_contract_data`(IN `id` INT(10))
Insert into tmp_contract_info (conid,localSymbol,DateC, timeC, openC, highC, low, closeC, volume, wap)

SELECT CONTRACTS.conid, CONTRACTS.localSymbol, LEFT(date,8),RIGHT(INFO.date,8),INFO.open,INFO.high,INFO.low,INFO.close,INFO.volume,INFO.WAP 
FROM tmp_info AS INFO
INNER JOIN fact_contracts AS CONTRACTS ON INFO.reqid = CONTRACTS.conid 
where INFO.REQID = ID  order by date ASC