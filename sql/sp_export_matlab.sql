CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_export_matlab`(IN `Symbol` VARCHAR(5))
begin
SELECT conid, CONCAT( DateC, ' ', timeC ) , openC, highC, low, closeC, volume, wap
FROM `fact_contract_info`
WHERE localSymbol = Symbol
ORDER BY CONCAT( DateC, ' ', timeC ) ASC
INTO OUTFILE 'C:\\BTi3\\Markets_2015\\2015\\data\\matlab\\Intraday\\Symbol.csv'
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '
';
end