# This is the modified Interactive Brokers Java client API library version 9.64.  

## Overview
Using Interactive Brokers browser based [API Gateway](https://www.interactivebrokers.com/java/classes/ibg.latest.jnlp), 
historical market data is captured and stored in MySQL for multi-threaded model processing. 

##Prerequisites
* Local instance of MySQL
* Create Database "ib" Collation UTF8_general_ci
* Create Tables - execute "sql/ib.sql"
* Create Stored Procedures by executing each sql file starting sp_
* Create User = "ib_admin" Password = "ib_admin" to MySQL localhost.
* Add mysql-connector-java-5.1.23-bin.jar to the Java Build Path Libraries. (picture below)
![alt text][mysql]

## Process
1. Launch IB API Gateway using the link provided above (Adjust Java security accordingly).
2. Launch the Java Application from this source code, Hour11.
3. Select Main >> Connect and change the port from "7496" to Interactive Broker's gateway port number "4001", select "OK"
4. Confirm your connection from the display panel listed as TWS Server. "Connected to Tws server version", or view the Gateway Log for 
 "JTS-EServerSocket-112: [0:47:76:1:0:0:0:SYS] Starting new conversation with client on /127.0.0.1"
5. Select Data >> Historical Data.  Enter the ContractID as the Message ID and add the Symbol... next select End Date, Duration, and Bar Size 
and select "OK".

## Summary - 
This client application will access Interactive Broker's Gateway and query various frequency data points of historical tick data 
recording the output to a local instance of MySQL that can be analyzed using relational database techniques.


[mysql]: https://github.com/btowner01/Interactive-Brokers/blob/data-collector/sql/add_mysql_to_classpath.png?raw=true "Add MySQL connector"
