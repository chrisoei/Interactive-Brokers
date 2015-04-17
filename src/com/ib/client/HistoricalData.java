/*
 * HistoricalData.java
 * Author = Ben Towner
 */
package com.ib.client;

import java.util.Vector;

public class HistoricalData implements Cloneable {
	public int    m_conId;
	
	public String m_date;
	public double m_open;
	public double m_high;
	public double m_low;
	public double m_close;
	public int m_volume;
	public double m_WAP;
	public String m_hasGaps;
	
	
    public HistoricalData() {
    	m_conId = 0;
    }

    public Object clone() throws CloneNotSupportedException {
        Contract retval = (Contract)super.clone();
        retval.m_comboLegs = (Vector)retval.m_comboLegs.clone();
        return retval;
    }

     
    public HistoricalData(String p_date, double p_open, double p_high, double p_low, double p_close, 
    			int p_volume, double p_WAP, String p_hasGaps ) {
    	
    	m_date = p_date;
        m_open = p_open;
        m_high = p_high;
        m_low = p_low;
        m_close = p_close;
        m_volume = p_volume;
        m_WAP = p_WAP;
        m_hasGaps = p_hasGaps ;
    	
    }
}