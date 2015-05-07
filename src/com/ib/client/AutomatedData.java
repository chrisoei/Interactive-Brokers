package com.ib.client;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bti3.datacollector.*;

public class AutomatedData {
	  private String  conId;
	  private int m_id;
	  private Connection connect = null;
	  private Statement statement = null;
	  private PreparedStatement preparedStatement = null;
	  private ResultSet resultSet = null;

	  public Contract m_contract = new Contract();
	  public AutomatedData(DataCollector dataCollector) {
		
	}

	public void readDataBase() throws Exception {
		  
    	    try {
    	      // This will load the MySQL driver
    	      Class.forName("com.mysql.jdbc.Driver");
    	      // Setup the connection with the DB
    	      connect = DriverManager
    	              .getConnection("jdbc:mysql://localhost/ib?"
    	                  + "user=ib&password=bt");
    	      // Statements allow to issue SQL queries to the database
    	      statement = connect.createStatement();
    	      // Result set get the result of the SQL query
    	      resultSet = statement
    	          .executeQuery("select conid from ib.contracts");
    	      
    	      writeResultSet(resultSet);

    	      
    	    } catch (Exception e) {
    	      throw e;
    	    } finally {
    	      close();
    	    }

    	  }

	public void readBackfill() throws Exception {
		  
	    try {
	      // This will load the MySQL driver
	      Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      connect = DriverManager
	              .getConnection("jdbc:mysql://localhost/ib?"
	                  + "user=ib&password=bt");
	      // Statements allow to issue SQL queries to the database
	      statement = connect.createStatement();
	      // Result set get the result of the SQL query
	      resultSet = statement
	          .executeQuery("select * from ib.backfill");
	      
	      writeResultSet(resultSet);

	      
	    } catch (Exception e) {
	      throw e;
	    } finally {
	      close();
	    }

	  }
	
	  private void writeMetaData(ResultSet resultSet) throws SQLException {
		    // Now get some metadata from the database
		    // Result set get the result of the SQL query
		    
		    System.out.println("The columns in the table are: ");
		    
		    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
		      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
		      
		      String ID = resultSet.getString("ID");
		      String ticker = resultSet.getString("ticker");
		      String industry = resultSet.getString("industry");
		      String sector = resultSet.getString("sector");
		    }
		  }
	
      private void writeResultSet(ResultSet resultSet) throws SQLException, IOException {
    	      	  
    	  while (resultSet.next()) {
    	     	
    		  conId = resultSet.getString("conid");
    		  
    		  m_contract.m_conId = readInt();
    		  m_id = readInt();
    		  // req mkt data
    	      //reqContractDetails(m_id, m_contract);
    		 
    	    }
    	    
    	  }
      
	protected int readInt() throws IOException {
          String str =  conId;
          return str == null ? 0 : Integer.parseInt( str);
      }
      
      private void close() {
    	    try {
    	      if (resultSet != null) {
    	        resultSet.close();
    	      }

    	      if (statement != null) {
    	        statement.close();
    	      }

    	      if (connect != null) {
    	        connect.close();
    	      }
    	    } catch (Exception e) {

    	    }
    	  }

 }