CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_clear_contract_data`(IN `id` INT)
delete from tmp_info where reqId = id