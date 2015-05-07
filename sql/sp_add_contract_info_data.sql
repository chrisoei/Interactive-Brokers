CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_add_contract_info_data`(IN `id` INT)
begin
Insert into tmp_contract_info (conid,localSymbol,DateC, timeC, openC, highC, low, closeC, volume, wap)

SELECT CONTRACTS.conid, CONTRACTS.localSymbol, LEFT(date,8),RIGHT(INFO.date,8),INFO.open,INFO.high,INFO.low,INFO.close,INFO.volume,INFO.WAP FROM tmp_info AS INFO INNER JOIN fact_contracts AS CONTRACTS ON INFO.reqid = CONTRACTS.conid where INFO.REQID = id  order by date ASC;

delete from tmp_info where reqId = id;

delete from tmp_contract_info where conid = id and DateC <= (select max(DateC) from fact_contract_info where conid = id);
delete from tmp_contract_info_stage where conid = id and DateC <= (select max(DateC) from fact_contract_info where conid = id);

insert into tmp_contract_info_stage select concat(conid," ", DateC," ",timeC) as data_key,conid,localSymbol,DateC,timeC,openC,highC,low,closeC,volume,wap from tmp_contract_info where conid = id;

insert into fact_contract_info select * from tmp_contract_info_stage group by concat(DateC,timeC) order by dateC desc;
        
delete from tmp_contract_info_stage where conid = id; 
delete from tmp_contract_info where conid = id and DateC <= (select max(DateC) from fact_contract_info where conid = id); 

end