package com.bti3.datacollector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.ib.client.ContractDetails;
import com.ib.client.HistoricalData;

public class DataInsert {
  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;

  public void readDataBase() throws Exception {
	    try {
	      // This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      connect = DriverManager
	          .getConnection("jdbc:mysql://localhost:3306/ib?"
	              + "user=ib_admin&password=ib_admin");
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
  
  public void writeContract(int reqId,ContractDetails contract) throws Exception {
    try {
      // This will load the MySQL driver
      Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      connect = DriverManager
          .getConnection("jdbc:mysql://localhost:3306/ib?"
              + "user=ib_admin&password=ib_admin");
      
      // Statements allow to issue SQL queries to the database
      statement = connect.createStatement();
      
      // PreparedStatements can use variables and are more efficient
      preparedStatement = connect
          .prepareStatement("insert into  ib.tmp_contracts values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
      // Parameters start with 1
      preparedStatement.setObject(1, contract.m_summary.m_conId);
      preparedStatement.setString(2, contract.m_summary.m_symbol);
      preparedStatement.setString(3, contract.m_summary.m_secType);
      preparedStatement.setString(4, contract.m_summary.m_expiry);
      preparedStatement.setObject(5, contract.m_summary.m_strike);
      preparedStatement.setString(6, contract.m_summary.m_right);
      preparedStatement.setObject(7, contract.m_summary.m_multiplier);
      preparedStatement.setString(8, contract.m_summary.m_exchange);
      preparedStatement.setString(9, contract.m_summary.m_primaryExch);
      preparedStatement.setString(10, contract.m_summary.m_currency);
      preparedStatement.setString(11, contract.m_summary.m_localSymbol);
      preparedStatement.setString(12, contract.m_marketName);
      preparedStatement.setString(13, contract.m_tradingClass);
      preparedStatement.setObject(14, contract.m_minTick);
      preparedStatement.setObject(15, contract.m_priceMagnifier);
      preparedStatement.setString(16, contract.m_orderTypes);
      preparedStatement.setString(17, contract.m_validExchanges);
      preparedStatement.setObject(18, contract.m_underConId);
      preparedStatement.setString(19, contract.m_longName);
      preparedStatement.setString(20, contract.m_contractMonth);
      preparedStatement.setString(21, contract.m_industry);
      preparedStatement.setString(22, contract.m_category);
      preparedStatement.setString(23, contract.m_subcategory);
      preparedStatement.setString(24, contract.m_timeZoneId);
      preparedStatement.setString(25, contract.m_tradingHours);
      preparedStatement.setString(26, contract.m_liquidHours);
      
      preparedStatement.executeUpdate();
    
    } catch (Exception e) {
      throw e;
    } finally {
      close();
    }

  }

  public void writeContractInfo(int reqId, HistoricalData historical) throws Exception {
	    try {
	      // This will load the MySQL driver
	      Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      connect = DriverManager
	          .getConnection("jdbc:mysql://localhost:3306/ib?"
	              + "user=ib_admin&password=ib_admin");
	      
	      // Statements allow to issue SQL queries to the database
	      statement = connect.createStatement();
	      
	      // PreparedStatements can use variables and are more efficient
	      preparedStatement = connect
	          .prepareStatement("insert into ib.tmp_info values (?,?,?,?,?,?,?,?,?)");
	      // Parameters start with 1
	      preparedStatement.setObject(1, reqId);
	      preparedStatement.setString(2, historical.m_date);
	      preparedStatement.setObject(3, historical.m_open);
	      preparedStatement.setObject(4, historical.m_high);
	      preparedStatement.setObject(5, historical.m_low);
	      preparedStatement.setObject(6, historical.m_close);
	      preparedStatement.setObject(7, historical.m_volume);
	      preparedStatement.setObject(8, historical.m_WAP);
	      preparedStatement.setString(9, historical.m_hasGaps);
	      
	      preparedStatement.executeUpdate();
	    
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
    }
  }


  private void writeResultSet(ResultSet resultSet) throws SQLException {
 
    // ResultSet is initially before the first data set
    while (resultSet.next()) {
      // It is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g. resultSet.getSTring(2);
     // Date date = result.Set.getDate(); -- as a sample of diff datatypes	

      String description = resultSet.getString("description");
      Date enddate = resultSet.getDate("enddate");
      
     
      System.out.println("Date: " + enddate);
      System.out.println("Description: " + description);
    }
    
  }

  // You need to close the resultSet
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