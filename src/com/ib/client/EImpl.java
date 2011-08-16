package com.ib.client;

public class EImpl implements EWrapper {

	@Override
	public void error(Exception e) {
		System.err.println("EImpl::error: " + e);
	}

	@Override
	public void error(String str) {
		System.err.println("EImpl::error: " + str);
	}

	@Override
	public void error(int id, int errorCode, String errorMsg) {
		System.err.println("EImpl::error: id = " + id + ", errorCode = " + errorCode + ": " + errorMsg);
	}

	@Override
	public void connectionClosed() {
		System.out.println("EImpl::connectionClosed: Connection closed.");
	}

	@Override
	public void tickPrice(int tickerId, int field, double price,
			int canAutoExecute) {
		System.out.println("EImpl::tickPrice: tickerID = " + tickerId + " field = " + field + " price = " + price +
				" canAutoExecute = " + canAutoExecute);
		
	}

	@Override
	public void tickSize(int tickerId, int field, int size) {
		System.out.println("EImpl::tickSize: tickerId = " + tickerId + " field = " + field + " size = " + size);
	}

	@Override
	public void tickOptionComputation(int tickerId, int field,
			double impliedVol, double delta, double optPrice,
			double pvDividend, double gamma, double vega, double theta,
			double undPrice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickGeneric(int tickerId, int tickType, double value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickString(int tickerId, int tickType, String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickEFP(int tickerId, int tickType, double basisPoints,
			String formattedBasisPoints, double impliedFuture, int holdDays,
			String futureExpiry, double dividendImpact, double dividendsToExpiry) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void orderStatus(int orderId, String status, int filled,
			int remaining, double avgFillPrice, int permId, int parentId,
			double lastFillPrice, int clientId, String whyHeld) {
		// TODO Auto-generated method stub
		
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
		System.out.println("EImpl::updateAccountTime: timeStamp = " + timeStamp);
	}

	@Override
	public void accountDownloadEnd(String accountName) {
		System.out.println("EImpl::accountDownloadEnd: accountName = " + accountName);
		
	}

	@Override
	public void nextValidId(int orderId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contractDetails(int reqId, ContractDetails contractDetails) {
		System.out.println("EImpl::contractDetails: reqId = " + reqId + " contractDetails = " + contractDetails);
		
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
		System.out.println("EImpl::realtimeBar: " +
			" reqId = " + reqId +
			" time = " + time +
			" open = " + open +
			" high = " + high +
			" low = " + low +
			" close = " + close +
			" volume = " + volume +
			" wap = " + wap +
			" count = " + count);
		
	}

	@Override
	public void currentTime(long time) {
		System.out.println("EImpl::currentTime: time = " + time);
		
	}

	@Override
	public void fundamentalData(int reqId, String data) {
		System.out.println("EImpl::fundamentalData: reqId = " + reqId + " data = "+ data);
		
	}

	@Override
	public void deltaNeutralValidation(int reqId, UnderComp underComp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tickSnapshotEnd(int reqId) {
		System.out.println("EImpl::tickSnapshotEnd: reqId = " + reqId);		
	}

}
