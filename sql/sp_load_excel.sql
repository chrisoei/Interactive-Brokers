CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_load_excel`(IN `ticker` VARCHAR(10))
insert into tmp_info (reqID, date, open, high, low, close, volume, wap) 

select conid, concat(DateC," ", timeC), openC, highC, low, closeC, volume, wap from fact_contract_info where localSymbol = ticker