/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ib.client;

/**
 *
 * @author BT
 */
public class BarSizeSetting2 {
    // constants - Duration from Historical Data Limitations
    public static final int Day1=1;
    public static final int Min30=2;
    public static final int Min15=3;
    public static final int Min3 =4;
    public static final int Min2=5;
    public static final int Min1=6;
    public static final int Secs30=7;
    public static final int Secs15=8;
    public static final int Secs5=9;
    public static final int Secs1=10;

    
    public static String getField( int barSizeSetting2) {
        switch( barSizeSetting2) {
        case Day1: return "1 day";
        case Min30: return "30 mins";
        case Min15: return "15 mins";
        case Min3 : return "3 mins";
        case Min2: return "2 mins";
        case Min1: return "1 min";
        case Secs30: return "30 secs";
        case Secs15: return "15 secs";
        case Secs5: return "5 secs";
        case Secs1: return "1secs";


            default:                          return "Unknown";
        }
    }
     public static String[] getFields(){
    	int totalFields = BarSizeSetting2.class.getFields().length;
    	String [] fields = new String[totalFields];
    	for (int i = 0; i < totalFields; i++){
    		fields[i] = BarSizeSetting2.getField(i + 1);
    	}
    	return fields;
    }       
}
