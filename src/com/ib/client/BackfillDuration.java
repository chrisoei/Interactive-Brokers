/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ib.client;

/**
 *
 * @author BT
 */
public class BackfillDuration {
    // constants - Duration from Historical Data Limitations
    public static final int ROLLANNUAL         = 1;
    public static final int SEMIROLLANNUAL     = 2;
    public static final int ROLLQTR            = 3;
    public static final int ROLLMTH            = 4;
    public static final int ROLLWEEK           = 5;
    public static final int BIDAILY            = 6;
    public static final int DAILY              = 7;
    public static final int HRS4               = 8;
    public static final int HRS2               = 9;
    public static final int HR1                = 10;
    public static final int MIN30              = 11;
    public static final int MIN15              = 12;
    public static final int MIN5               = 13;
    public static final int MIN1               = 14;
    
    public static String getField( int backfillDuration) {
        switch( backfillDuration) {
            case ROLLANNUAL	: return "1 Y";
            case SEMIROLLANNUAL	: return "6 M";
            case ROLLQTR	: return "3 M";
            case ROLLMTH	: return "1 M";
            case ROLLWEEK	: return "1 W";
            case BIDAILY	: return "2 D";
            case DAILY	: return "1 D";
            case HRS4	: return "14400 S";
            case HRS2	: return "7200 S";
            case HR1	: return "3600 S";
            case MIN30	: return "1800 S";
            case MIN15	: return "960 S";
            case MIN5	: return "300 S";
            case MIN1	: return "60 S";

            default:                          return "Unknown";
        }
    }
     public static String[] getFields(){
    	int totalFields = BackfillDuration.class.getFields().length;
    	String [] fields = new String[totalFields];
    	for (int i = 0; i < totalFields; i++){
    		fields[i] = BackfillDuration.getField(i + 1);
    	}
    	return fields;
    }       
}
