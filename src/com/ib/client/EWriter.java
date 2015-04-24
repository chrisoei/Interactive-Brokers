/*
 * EWriter.java
 * 
 * Author - Ben Towner
 * Date - 10/04/2013
 * Write message data into database
 *  
 */


package com.ib.client;

import com.bti3.datacollector.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;

public class EWriter extends Thread {

	// incoming msg id's from EReader
    
    static final int CONTRACT_DATA      = 10;
    static final int HISTORICAL_DATA    = 17;

    private EClientSocket 	m_parent;
    private DataInputStream m_dis;

    protected EClientSocket parent()    { return m_parent; }
    private EWrapper eWrapper()         { return (EWrapper)parent().wrapper(); }
    //private DataInsert DataInsert()		{ return (DataInsert)parent().EWriter()}
    
    public EWriter( EClientSocket parent, DataInputStream dis) {
        this("EWriter", parent, dis);
    }
    
    protected EWriter( String name, EClientSocket parent, DataInputStream dis) {
    	setName( name);
        m_parent = parent;
        m_dis = dis;
    }
    
    public void run() {
        try {
            // loop until thread is terminated
            while( !isInterrupted() && processMsg(readInt()));
        }
        catch ( Exception ex ) {
        	if (parent().isConnected()) {
        		eWrapper().error( ex);
        	}
        }
        if (parent().isConnected()) {
        	m_parent.close();
        }
    }
    
    /** Overridden in subclass. 
     * @throws Exception */
    protected boolean processMsg(int msgId) throws Exception{
        if( msgId == -1) return false;
        
        switch( msgId) {
        case CONTRACT_DATA: {
            int version = readInt();
            
            int reqId = -1;
            if (version >= 3) {
            	reqId = readInt();
            }

            ContractDetails contract = new ContractDetails();
            contract.m_summary.m_symbol = readStr();
            contract.m_summary.m_secType = readStr();
            contract.m_summary.m_expiry = readStr();
            contract.m_summary.m_strike = readDouble();
            contract.m_summary.m_right = readStr();
            contract.m_summary.m_exchange = readStr();
            contract.m_summary.m_currency = readStr();
            contract.m_summary.m_localSymbol = readStr();
            contract.m_marketName = readStr();
            contract.m_tradingClass = readStr();
            contract.m_summary.m_conId = readInt();
            contract.m_minTick = readDouble();
            contract.m_summary.m_multiplier = readStr();
            contract.m_orderTypes = readStr();
            contract.m_validExchanges = readStr();
            if (version >= 2) {
                contract.m_priceMagnifier = readInt();
            }
            if (version >= 4) {
            	contract.m_underConId = readInt();
            }
            if( version >= 5) {
               contract.m_longName = readStr();
               contract.m_summary.m_primaryExch = readStr();
            }
            if( version >= 6) {
                contract.m_contractMonth = readStr();
                contract.m_industry = readStr();
                contract.m_category = readStr();
                contract.m_subcategory = readStr();
                contract.m_timeZoneId = readStr();
                contract.m_tradingHours = readStr();
                contract.m_liquidHours = readStr();
             }
            eWrapper().contractDetails( reqId, contract);
                        
            DataInsert dao = new DataInsert();
            dao.writeContract(reqId,contract);
		 
            break;
        }
        
        case HISTORICAL_DATA: {
        	HistoricalData historical = new HistoricalData();
        	
        	int version = readInt();
            int reqId = readInt();
      	  	String startDateStr;
      	  	String endDateStr;
      	  	String completedIndicator = "finished";
            if (version >= 2) {
          	  startDateStr = readStr();
          	  endDateStr = readStr();
          	  completedIndicator += "-" + startDateStr + "-" + endDateStr;
            }
            int itemCount = readInt();
            DataInsert dao = new DataInsert();
            for (int ctr = 0; ctr < itemCount; ctr++) {
              
              historical.m_date = readStr();
              historical.m_open = readDouble();
              historical.m_high = readDouble();
              historical.m_low = readDouble();
              historical.m_close = readDouble();
              historical.m_volume = readInt();
              historical.m_WAP = readDouble();
              historical.m_hasGaps = readStr();
              int barCount = -1;
              if (version >= 3) {
              	barCount = readInt();
              }
              eWrapper().historicalData(reqId, historical.m_date, historical.m_open, historical.m_high, historical.m_low,
            		  historical.m_close, historical.m_volume, barCount, historical.m_WAP,
                                      Boolean.valueOf(historical.m_hasGaps).booleanValue());
              
             dao.writeContractInfo(reqId, historical); 
            }
            // send end of dataset marker
            eWrapper().historicalData(reqId, completedIndicator, -1, -1, -1, -1, -1, -1, -1, false);
            
            
            
            
            break;
          }
        
        }
        return true;
    }
    
    protected String readStr() throws IOException {
        StringBuffer buf = new StringBuffer();
        while( true) {
            byte c = m_dis.readByte();
            if( c == 0) {
                break;
            }
            buf.append( (char)c);
        }

        String str = buf.toString();
        return str.length() == 0 ? null : str;
    }


    boolean readBoolFromInt() throws IOException {
        String str = readStr();
        return str == null ? false : (Integer.parseInt( str) != 0);
    }

    protected int readInt() throws IOException {
        String str = readStr();
        return str == null ? 0 : Integer.parseInt( str);
    }
    
    protected int readIntMax() throws IOException {
        String str = readStr();
        return (str == null || str.length() == 0) ? Integer.MAX_VALUE
        	                                      : Integer.parseInt( str);
    }
    
    protected long readLong() throws IOException {
        String str = readStr();
        return str == null ? 0l : Long.parseLong(str);
    }

    protected double readDouble() throws IOException {
        String str = readStr();
        return str == null ? 0 : Double.parseDouble( str);
    }
    
    protected double readDoubleMax() throws IOException {
        String str = readStr();
        return (str == null || str.length() == 0) ? Double.MAX_VALUE
        	                                      : Double.parseDouble( str);
    }
}
