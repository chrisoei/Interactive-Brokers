package com.bti3global.hour11;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.text.DefaultEditorKit;

import com.ib.client.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

import java.util.Random;
import java.util.Scanner;

/**
 * @author BT
 */
public class DataCollector extends JFrame implements EWrapper {
	private static final int NOT_AN_FA_ACCOUNT_ERROR = 321 ;
    private int faErrorCodes[] = { 503, 504, 505, 522, 1100, NOT_AN_FA_ACCOUNT_ERROR } ;
    private boolean faError ;

    private EClientSocket   m_client = new EClientSocket( this);
    private IBTextPanel     m_tickers = new IBTextPanel("Market / Historical Data", false);
    private IBTextPanel     m_TWS = new IBTextPanel("TWS Server ", false);
    private IBTextPanel     m_errors = new IBTextPanel("Errors and Messages", false);
    private JMenuBar        menuBar = new JMenuBar();
    private JMenu           mainMenu = new JMenu("Main");
    private JMenu           dataMenu = new JMenu("Data");
    
    private boolean m_disconnectInProgress = false;
    
    private OrderDlg      m_orderDlg = new OrderDlg(this);

    public boolean  m_bIsFAAccount = false;
    
    DataCollector(){

        JPanel scrollingWindowDisplayPanel = new JPanel( new GridLayout( 0, 1) );
        scrollingWindowDisplayPanel.add( m_tickers);
        scrollingWindowDisplayPanel.add( m_TWS);
        scrollingWindowDisplayPanel.add( m_errors);
             
        menuBar.add(mainMenu);
        this.setJMenuBar(menuBar);
        JMenuItem connectMenuItem = new JMenuItem();
        connectMenuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e) {
                onConnect();
            }
        });
        
        JMenuItem disconnectMenuItem = new JMenuItem();
        disconnectMenuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e) {
                onDisconnect();
            }
        });
        JMenuItem currentTimeMenuItem = new JMenuItem();
        currentTimeMenuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e) {
                onReqCurrentTime();
            }
        });
        
        JMenuItem clearMenuItem = new JMenuItem();
        clearMenuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e) {
                onClear();
            }
        });
        
        JMenuItem closeMenuItem = new JMenuItem();
        closeMenuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e) {
                onClose();
            }
        });
        
        connectMenuItem.setText("Connect");
        disconnectMenuItem.setText("Disconnect");
        currentTimeMenuItem.setText("Current Time");
        clearMenuItem.setText("Clear");
        closeMenuItem.setText("Exit");
        
        mainMenu.add(connectMenuItem);
        mainMenu.add(disconnectMenuItem);
        mainMenu.add(currentTimeMenuItem);
        mainMenu.add(clearMenuItem);
        mainMenu.add(closeMenuItem);
        menuBar.add(dataMenu);
        
        this.setJMenuBar(menuBar);
        
        JMenuItem histDataMenuItem = new JMenuItem();
        histDataMenuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e) {
                onHistoricalData();
            }
        });
       
        JMenuItem reqContractMenuItem = new JMenuItem();
        reqContractMenuItem.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e) {
                onReqContractData();
            }
        });
        
        histDataMenuItem.setText("Historical Data");
        reqContractMenuItem.setText("Contract Data");
        
        dataMenu.add(histDataMenuItem);
        dataMenu.add(reqContractMenuItem);
       
        JPanel buttonPanel = createButtonPanel();
        getContentPane().add( scrollingWindowDisplayPanel, BorderLayout.CENTER);
        getContentPane().add( buttonPanel, BorderLayout.EAST);
        setSize( 1000, 1000);
        setTitle("Hour 11");
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
    
    }
    
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel( new GridLayout( 0, 1) );
          
        JButton butServerLogging = new JButton( "Server Logging");
        butServerLogging.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e) {
                onServerLogging();
            }
        });
              
        JButton butConnectDatabase = new JButton( "Database Connect");
        butConnectDatabase.addActionListener( new ActionListener() {
            public void actionPerformed( ActionEvent e) {
                onConnectDatabase();
            }
        });
        
        buttonPanel.add(butConnectDatabase);
  
        return buttonPanel;
    }
    
    void onConnectDatabase(){
    	// Add call to Database Stored-Procedures
    }
	
    void onServerLogging() {
        // get server logging level
        LogConfigDlg dlg = new LogConfigDlg( this);
        dlg.setVisible(true);
        if( !dlg.m_rc) {
            return;
        }

        // connect to TWS
        m_client.setServerLogLevel( dlg.m_serverLogLevel);
    }
    
    void onConnect() {
        m_bIsFAAccount = false;
        // get connection parameters
        ConnectDlg dlg = new ConnectDlg( this);
        dlg.setVisible(true);
        if( !dlg.m_rc) {
            return;
        }

        // connect to TWS
        m_disconnectInProgress = false;
        
        m_client.eConnect( dlg.m_retIpAddress, dlg.m_retPort, dlg.m_retClientId);
        if (m_client.isConnected()) {
            m_TWS.add("Connected to Tws server version " +
                       m_client.serverVersion() + " at " +
                       m_client.TwsConnectionTime());
        }
    }
    
    void onDisconnect() {
        // disconnect from TWS
        m_disconnectInProgress = true;
        m_client.eDisconnect();
    }
 
    void onReqCurrentTime() {
    	m_client.reqCurrentTime();
	}
    
    void onClear() {
        m_tickers.clear();
        m_TWS.clear();
        m_errors.clear();
    }
    
    void onClose() {
        System.exit(1);
    }
    
    void onHistoricalData() {
        // run m_orderDlg
        m_orderDlg.show();
        if( !m_orderDlg.m_rc ) {
            return;
        }
        
        if( Util.StringCompare( m_orderDlg.m_whatToShow, "estimates" ) == 0 ||
        	Util.StringCompare( m_orderDlg.m_whatToShow, "finstat"   ) == 0 ||
        	Util.StringCompare( m_orderDlg.m_whatToShow, "snapshot"  ) == 0 ) {
        	
        	m_client.reqFundamentalData(m_orderDlg.m_id, m_orderDlg.m_contract,
        			/* reportType */ m_orderDlg.m_whatToShow);
        	return;
        }

        // req historical data
        m_client.reqHistoricalData( m_orderDlg.m_id, m_orderDlg.m_contract,
                                    m_orderDlg.m_backfillEndTime, m_orderDlg.m_backfillDuration,
                                    m_orderDlg.m_barSizeSetting, m_orderDlg.m_whatToShow,
                                    m_orderDlg.m_useRTH, m_orderDlg.m_formatDate );
    }
    void onReqContractData() {
        // run m_orderDlg
        m_orderDlg.show();
        if( !m_orderDlg.m_rc ) {
            return;
        }

        // req mkt data
        m_client.reqContractDetails( m_orderDlg.m_id, m_orderDlg.m_contract );
    }

	@Override
	public void error(Exception e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(String str) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void error(int id, int errorCode, String errorMsg) {
		// TODO Auto-generated method stub
		
	}

	  public void connectionClosed() {
	        String msg = EWrapperMsgGenerator.connectionClosed();
	        Hour11.inform( this, msg);
	    }

	  public void tickPrice( int tickerId, int field, double price, int canAutoExecute) {
	        // received price tick
	    	String msg = EWrapperMsgGenerator.tickPrice( tickerId, field, price, canAutoExecute);
	        m_tickers.add( msg );
	    }
	    public void tickSize( int tickerId, int field, int size) {
	        // received size tick
	    	String msg = EWrapperMsgGenerator.tickSize( tickerId, field, size);
	        m_tickers.add( msg);
	    }
	    public void tickOptionComputation( int tickerId, int field, double impliedVol, double delta, double optPrice, double pvDividend,
	        double gamma, double vega, double theta, double undPrice) {
	        // received computation tick
	        String msg = EWrapperMsgGenerator.tickOptionComputation( tickerId, field, impliedVol, delta, optPrice, pvDividend,
	            gamma, vega, theta, undPrice);
	        m_tickers.add( msg );
	    }
	    public void tickGeneric( int tickerId, int tickType, double value) {
	        // received generic tick
	    	String msg = EWrapperMsgGenerator.tickGeneric(tickerId, tickType, value);
	        m_tickers.add( msg);
	    }
	    public void tickString( int tickerId, int tickType, String value) {
	        // received String tick
	    	String msg = EWrapperMsgGenerator.tickString(tickerId, tickType, value);
	        m_tickers.add( msg);
	    }
	    public void tickEFP(int tickerId, int tickType, double basisPoints, String formattedBasisPoints,
	    	double impliedFuture, int holdDays, String futureExpiry, double dividendImpact,
	    	double dividendsToExpiry) {
	        // received EFP tick
	    	String msg = EWrapperMsgGenerator.tickEFP(tickerId, tickType, basisPoints, formattedBasisPoints,
					impliedFuture, holdDays, futureExpiry, dividendImpact, dividendsToExpiry);
	        m_tickers.add(msg);
	    }

	    public void orderStatus( int orderId, String status, int filled, int remaining,
	            double avgFillPrice, int permId, int parentId,
	        	double lastFillPrice, int clientId, String whyHeld) {
	            throw new UnsupportedOperationException("Not supported yet.");
	        }
	    
	@Override
	public void openOrder(int orderId, Contract contract, Order order,
			OrderState orderState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openOrderEnd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAccountValue(String key, String value, String currency,
			String accountName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePortfolio(Contract contract, int position,
			double marketPrice, double marketValue, double averageCost,
			double unrealizedPNL, double realizedPNL, String accountName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAccountTime(String timeStamp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accountDownloadEnd(String accountName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nextValidId(int orderId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contractDetails(int reqId, ContractDetails contractDetails) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void bondContractDetails(int reqId, ContractDetails contractDetails) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contractDetailsEnd(int reqId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execDetails(int reqId, Contract contract, Execution execution) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execDetailsEnd(int reqId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMktDepth(int tickerId, int position, int operation,
			int side, double price, int size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMktDepthL2(int tickerId, int position,
			String marketMaker, int operation, int side, double price, int size) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNewsBulletin(int msgId, int msgType, String message,
			String origExchange) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void managedAccounts(String accountsList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveFA(int faDataType, String xml) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void historicalData(int reqId, String date, double open,
			double high, double low, double close, int volume, int count,
			double WAP, boolean hasGaps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scannerParameters(String xml) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scannerData(int reqId, int rank,
			ContractDetails contractDetails, String distance, String benchmark,
			String projection, String legsStr) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scannerDataEnd(int reqId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void realtimeBar(int reqId, long time, double open, double high,
			double low, double close, long volume, double wap, int count) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void currentTime(long time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fundamentalData(int reqId, String data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deltaNeutralValidation(int reqId, UnderComp underComp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickSnapshotEnd(int reqId) {
		// TODO Auto-generated method stub
		
	}
}

