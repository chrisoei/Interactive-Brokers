/*
 * Contract.java
 *
 */
package com.ib.client;

import java.util.Vector;

public class Contract implements Cloneable {

	/**
	 * Contract ID
	 */
	public int    m_conId;
    /**
     * Symbol (example: MSFT)
     */
    public String m_symbol;
    /**
     * Security Type (STK for stocks)
     */
    public String m_secType;
    public String m_expiry;
    public double m_strike;
    public String m_right;
    public String m_multiplier;
    /**
     * Exchange (example: SMART)
     */
    public String m_exchange;

    /**
     * Currency (example: USD)
     */
    public String m_currency;
    public String m_localSymbol;
    /**
     * Primary Exchange. Pick a non-aggregate (ie not the SMART exchange) exchange that the contract trades on.  DO NOT SET TO SMART.
     */
    public String m_primaryExch;
    /**
     * Include Expired. Can not be set to true for orders
     */
    public boolean m_includeExpired;
    
    /**
     * Security ID type: CUSIP, SEDOL, ISIN, or RIC.
     */
    public String m_secIdType;
    
    /**
     * Security ID.
     */
    public String m_secId;
    
    // COMBOS
    public String m_comboLegsDescrip; // received in open order version 14 and up for all combos
    public Vector m_comboLegs = new Vector();
    
    // delta neutral
    public UnderComp m_underComp;

    public Contract() {
    	m_conId = 0;
        m_strike = 0;
        m_includeExpired = false;
    }

    public Object clone() throws CloneNotSupportedException {
        Contract retval = (Contract)super.clone();
        retval.m_comboLegs = (Vector)retval.m_comboLegs.clone();
        return retval;
    }

    /**
     * @param p_conId			Contract ID
     * @param p_symbol			Symbol (example: MSFT)
     * @param p_secType			Security Type (example: STK for stock)
     * @param p_expiry			Expiry
     * @param p_strike			Strike
     * @param p_right			Put/Call
     * @param p_multiplier		Option Multiplier
     * @param p_exchange		Exchange (example: SMART)
     * @param p_currency		Currency (example: USD)
     * @param p_localSymbol		Local Symbol
     * @param p_comboLegs		
     * @param p_primaryExch		Primary Exchange (example: ISLAND)
     * @param p_includeExpired	Include Expired
     * @param p_secIdType 		Security ID Type (CUSIP;SEDOL;ISIN;RIC)
     * @param p_secId			Security ID
     */
    public Contract(int p_conId, String p_symbol, String p_secType, String p_expiry,
                    double p_strike, String p_right, String p_multiplier,
                    String p_exchange, String p_currency, String p_localSymbol,
                    Vector p_comboLegs, String p_primaryExch, boolean p_includeExpired, 
                    String p_secIdType, String p_secId) {
    	m_conId = p_conId;
        m_symbol = p_symbol;
        m_secType = p_secType;
        m_expiry = p_expiry;
        m_strike = p_strike;
        m_right = p_right;
        m_multiplier = p_multiplier;
        m_exchange = p_exchange;
        m_currency = p_currency;
        m_includeExpired = p_includeExpired;
        m_localSymbol = p_localSymbol;
        m_comboLegs = p_comboLegs;
        m_primaryExch = p_primaryExch;
        m_secIdType = p_secIdType;
        m_secId = p_secId ;
    }

    public boolean equals(Object p_other) {

    	if (this == p_other) {
    		return true;
    	}

    	if (p_other == null || !(p_other instanceof Contract)) {
    		return false;
    	}

        Contract l_theOther = (Contract)p_other;
        
        if (m_conId != l_theOther.m_conId) {
        	return false;
        }

        if (Util.StringCompare(m_secType, l_theOther.m_secType) != 0) {
        	return false;
        }

        if (Util.StringCompare(m_symbol, l_theOther.m_symbol) != 0 ||
        	Util.StringCompare(m_exchange, l_theOther.m_exchange) != 0 ||
        	Util.StringCompare(m_primaryExch, l_theOther.m_primaryExch) != 0 ||
        	Util.StringCompare(m_currency, l_theOther.m_currency) != 0) {
        	return false;
        }

        if (!Util.NormalizeString(m_secType).equals("BOND")) {

        	if (m_strike != l_theOther.m_strike) {
        		return false;
        	}

        	if (Util.StringCompare(m_expiry, l_theOther.m_expiry) != 0 ||
        		Util.StringCompare(m_right, l_theOther.m_right) != 0 ||
        		Util.StringCompare(m_multiplier, l_theOther.m_multiplier) != 0 ||
        		Util.StringCompare(m_localSymbol, l_theOther.m_localSymbol) != 0) {
        		return false;
        	}
        }

        if (Util.StringCompare(m_secIdType, l_theOther.m_secIdType) != 0) {
        	return false;
        }
        
        if (Util.StringCompare(m_secId, l_theOther.m_secId) != 0) {
        	return false;
        }

    	// compare combo legs
        if (!Util.VectorEqualsUnordered(m_comboLegs, l_theOther.m_comboLegs)) {
        	return false;
        }
        
        if (m_underComp != l_theOther.m_underComp) {
        	if (m_underComp == null || l_theOther.m_underComp == null) {
        		return false;
        	}
        	if (!m_underComp.equals(l_theOther.m_underComp)) {
        		return false;
        	}
        }

        return true;
    }
}