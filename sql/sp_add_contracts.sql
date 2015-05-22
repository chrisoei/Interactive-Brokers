CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_add_contracts`()
begin

INSERT INTO `fact_contracts` (`conid`, `symbol`, `secType`, `expiry`, `strike`, `rightOPT`, `multiplier`, `exchange`, `primaryExch`, `currency`, `localSymbol`, `marketName`, `tradingClass`, `minTick`, `priceMagnifier`, `orderTypes`, `validExchanges`, `underConid`, `longName`, `contractMonth`, `industry`, `category`, `subcategory`, `timeZoneid`, `tradingHours`, `liquidHours`) 

SELECT `tmp`.`conid`,`tmp`.`symbol`,`tmp`.`secType`,`tmp`.`expiry`,`tmp`.`strike`,`tmp`.`rightOPT`,`tmp`.`multiplier`,`tmp`.`exchange`,`tmp`.`primaryExch`,`tmp`.`currency`,`tmp`.`localSymbol`,`tmp`.`marketName`,`tmp`.`tradingClass`,`tmp`.`minTick`,`tmp`.`priceMagnifier`,`tmp`.`orderTypes`,`tmp`.`validExchanges`,`tmp`.`underConid`,`tmp`.`longName`,`tmp`.`contractMonth`,`tmp`.`industry`,`tmp`.`category`,`tmp`.`subcategory`,`tmp`.`timeZoneid`,`tmp`.`tradingHours`,`tmp`.`liquidHours`
FROM tmp_contracts AS tmp
LEFT JOIN fact_contracts AS fact ON fact.conid = tmp.conid
WHERE fact.conid IS NULL;
end