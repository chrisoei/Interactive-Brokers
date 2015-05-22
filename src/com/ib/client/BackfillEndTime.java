/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ib.client;


/**
 *
 * @author BT
 * 
 */
public class BackfillEndTime<EndDate> {
    // constants - EndTime from Historical Data Limitations
    
    
    		public static final int Week1	=	1;
    		public static final int Week2	=	2;
    		public static final int Week3	=	3;
    		public static final int Week4	=	4;
    		public static final int Week5	=	5;
    		public static final int Week6	=	6;
    		public static final int Week7	=	7;
    		public static final int Week8	=	8;
    		public static final int Week9	=	9;
    		public static final int Week10	=	10;
    		public static final int Week11	=	11;
    		public static final int Week12	=	12;
    		public static final int Week13	=	13;
    		public static final int Week14	=	14;
    		public static final int Week15	=	15;
    		public static final int Week16	=	16;
    		public static final int Week17	=	17;
    		public static final int Week18	=	18;
    		public static final int Week19	=	19;
    		public static final int Week20	=	20;
    		public static final int Week21	=	21;
    		public static final int Week22	=	22;
    		public static final int Week23	=	23;
    		public static final int Week24	=	24;
    		public static final int Week25	=	25;
    		public static final int Week26	=	26;
    		public static final int Week27	=	27;
    		public static final int Week28	=	28;
    		public static final int Week29	=	29;
    		public static final int Week30	=	30;
    		public static final int Week31	=	31;
    		public static final int Week32	=	32;
    		public static final int Week33	=	33;
    		public static final int Week34	=	34;
    		public static final int Week35	=	35;
    		public static final int Week36	=	36;
    		public static final int Week37	=	37;
    		public static final int Week38	=	38;
    		public static final int Week39	=	39;
    		public static final int Week40	=	40;
    		public static final int Week41	=	41;
    		public static final int Week42	=	42;
    		public static final int Week43	=	43;
    		public static final int Week44	=	44;
    		public static final int Week45	=	45;
    		public static final int Week46	=	46;
    		public static final int Week47	=	47;
    		public static final int Week48	=	48;
    		public static final int Week49	=	49;
    		public static final int Week50	=	50;
    		public static final int Week51	=	51;
    		public static final int Week52	=	52;
	
    
    public static String getField( int backfillEndTime) {
        switch( backfillEndTime) {
        
        case Week1:return "20150106 00:00:00 GMT";
        case Week2:return "20150113 00:00:00 GMT";
        case Week3:return "20150120 00:00:00 GMT";
        case Week4:return "20150127 00:00:00 GMT";
        case Week5:return "20150203 00:00:00 GMT";
        case Week6:return "20150210 00:00:00 GMT";
        case Week7:return "20150217 00:00:00 GMT";
        case Week8:return "20150224 00:00:00 GMT";
        case Week9:return "20150303 00:00:00 GMT";
        case Week10:return "20150310 00:00:00 GMT";
        case Week11:return "20150317 00:00:00 GMT";
        case Week12:return "20150324 00:00:00 GMT";
        case Week13:return "20150331 00:00:00 GMT";
        case Week14:return "20150407 00:00:00 GMT";
        case Week15:return "20150414 00:00:00 GMT";
        case Week16:return "20150421 00:00:00 GMT";
        case Week17:return "20150428 00:00:00 GMT";
        case Week18:return "20150505 00:00:00 GMT";
        case Week19:return "20150512 00:00:00 GMT";
        case Week20:return "20150519 00:00:00 GMT";
        case Week21:return "20150526 00:00:00 GMT";
        case Week22:return "20150602 00:00:00 GMT";
        case Week23:return "20150609 00:00:00 GMT";
        case Week24:return "20150616 00:00:00 GMT";
        case Week25:return "20150623 00:00:00 GMT";
        case Week26:return "20150630 00:00:00 GMT";
        case Week27:return "20150707 00:00:00 GMT";
        case Week28:return "20150714 00:00:00 GMT";
        case Week29:return "20150721 00:00:00 GMT";
        case Week30:return "20150728 00:00:00 GMT";
        case Week31:return "20150804 00:00:00 GMT";
        case Week32:return "20150811 00:00:00 GMT";
        case Week33:return "20150818 00:00:00 GMT";
        case Week34:return "20150825 00:00:00 GMT";
        case Week35:return "20150901 00:00:00 GMT";
        case Week36:return "20150908 00:00:00 GMT";
        case Week37:return "20150915 00:00:00 GMT";
        case Week38:return "20150922 00:00:00 GMT";
        case Week39:return "20150929 00:00:00 GMT";
        case Week40:return "20151006 00:00:00 GMT";
        case Week41:return "20151013 00:00:00 GMT";
        case Week42:return "20151020 00:00:00 GMT";
        case Week43:return "20151027 00:00:00 GMT";
        case Week44:return "20151103 00:00:00 GMT";
        case Week45:return "20151110 00:00:00 GMT";
        case Week46:return "20151117 00:00:00 GMT";
        case Week47:return "20151124 00:00:00 GMT";
        case Week48:return "20151201 00:00:00 GMT";
        case Week49:return "20151208 00:00:00 GMT";
        case Week50:return "20151215 00:00:00 GMT";
        case Week51:return "20151222 00:00:00 GMT";
        case Week52:return "20151231 00:00:00 GMT";
	


        
    default:                          return "Unknown";
        }
    }
    
    public static String[] getFields(){
    	int totalFields = BackfillEndTime.class.getFields().length;
    	String [] fields = new String[totalFields];
    	for (int i = 0; i < totalFields; i++){
    		fields[i] = BackfillEndTime.getField(i + 1);
    	}
    	return fields;
    }    
    
     
}
