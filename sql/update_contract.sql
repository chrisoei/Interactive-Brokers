CREATE DEFINER=`root`@`localhost` PROCEDURE `update_contract`()
begin 
INSERT INTO bt_ib.tbl_contract_info (conid, DateC, timeC, openC, highC, low, closeC, volume, wap)
select conid, DateC, timeC, openC, highC, low, closeC, volume, wap from bt_ib.tmp_contract_info;

end