/**
 * 
 */
package com.bdp.test.constants;

/**
 * @author Rehan Azam, Vishal Makadia
 * @version 1.0
 * @since 2016-01-01
 * 
 */
public interface BDPConstants {
	int ZERO = 0;
	int ONE = 1;
	int TWO = 2;
	int THREE = 3;
	String EQUALL="=";
	String DATE_PATTERN_TS="YYY-MM-DD";
	String START_TIME="START_TIME";
	
	String SPACE = " ";
	String STRING_SPACE = "\\s";
	
	String NEW_LINE = "\n";

	String CTRL_A = "\001";

	// hadoop specific constants
	String MAPRED_JOB_QUEUE_NAME = "mapreduce.job.queuename";

	String COMMA = "\\,";
	String DOT = ".";
	String HYPHEN = "\\-";
	String STRING_ZERO = "0";
	String STRING_ONE = "1";
	String STRING_MINUS_ONE = "-1";
	String STRING_CTRL_A_DELIMITER = "\\001";
	String SEMICOLON=";";
	String PIPE = "\\|";
	//String CS_DELIMETER = "|";
	String CS_DELIMETER = "\001";
	
	String OUTPUT_PATH = "output.path";
	
	//HBase properties
	String HBASE_TABLE_NAME = "hbase.table.name";
	String HBASE_COLUMN_NAME = "hbase.column.name";
	String HBASE_CONFIG_ZOOKEEPER_CLIENT_PORT = "yodlee.config.zookeeper.client.port";
	String HBASE_ZOOKEEPER_CLIENT_PORT = "yodlee.zookeeper.port";
	String HBASE_CONFIG_ZOOKEEPER_QUORUM = "yodlee.config.hbase.quorum";
	String HBASE_ZOOKEEPER_SERVER = "yodlee.hbase.quorum";
	String HBASE_ROWKEY_INDEX = "hbase.rowkey.index";
	String HBASE_TABLE_COLUMN_FAMILY = "hbase.table.column.family";
	
	
	String STORE_SALES_TABLE_NAME = "store.sales.table.name";
	String ONLINE_SALES_TABLE_NAME = "online.sales.table.name";
	String STORE_SALES_COLUMN_FAMILY_NAME = "store.sales.columnfamily.name";
	String ONLINE_SALES_COLUMN_FAMILY_NAME = "online.sales.columnfamily.name";
	String STORE_SALES_COLUMN_NAME = "store.sales.column.name";
	String ONLINE_SALES_COLUMN_NAME = "online.sales.column.name";
	String TOTAL_SALES_HBASE_TABLE_NAME = "totalsales.hbase.table.name";
	String TOTAL_SALES_COLUMN_FAMILY = "total.sales.columnfamily.name";
	String TOTAL_SALES_COLUMN_NAME = "total.sales.column.name";
	
	String YODLEE_LOOKUP_TABLE_NAME = "yodlee.lookup.table";
	String YODLEE_LOOKUP_COLUMN_FAMILY="yodlee.lookup.family";
	String YODLEE_LOOKUP_COLUMN_NAME="yodlee.lookup.column";
	
	String YODLEE_SNAPSHOT_TABLE_NAME = "yodlee.snapshot.table";
	String YODLEE_SNAPSHOT_COLUMN_FAMILY = "yodlee.snapshot.family";
	String YODLEE_SNAPSHOT_COLUMN_NAME = "yodlee.snapshot.column";
	
	String CUST_XREF_ID="cust_xref_id";
	String CM15="cm15";
	String ACCT_DEROG_IND="acct_derog_ind";
	
	String HiveServer2driverName = "org.apache.hive.jdbc.HiveDriver";
	String CREATE_COMMAND = "CREATE EXTERNAL TABLE IF NOT EXISTS";
	String INSERT_COMMAND = "INSERT OVERWRITE TABLE";
	String INSERT_OVERWRITE_DIR_COMMAND = "INSERT OVERWRITE DIRECTORY";
	String LOCATION = "LOCATION";
	String QUOTE = "'";
	String ROW_FORMAT_DELIMITED = "ROW FORMAT DELIMITED";
	String FIELDS_TERMINATED = "FIELDS TERMINATED BY";
	
	String ODL_CUSTOMER_COLUMN_NAME="odl.customer.column.name";
	String ODL_ACCOUNT_COLUMN_NAME="odl.account.column.name";	
	
	String LAST_UPDATED="LAST_UPDATED";
	String ERROR_NODE="ERROR_NODE";
	
	String START_TIME_FORMAT="00:00:00.000";
	String END_TIME_FORMAT="00:05:00.000";
	
	String SUCCESS="success";
	String HBASE_EXTRACT="hbase-extract";
	String CS_LOAD="cornerstone-ingestion";
	String CS_LOAD_BANK="load-bank-data";
	String CS_LOAD_CREDIT="load-credit-data";
	String CS_LOAD_STOCK="load-stock-data";
	String CS_LOAD_LOAN="load-loan-data";
	String CS_LOAD_HOLDING="load-holding-data";
	String CS_LOAD_TRANSACTION="load-transaction-data";
	String WORKFLOW_FAILED_NODE="workflow_faile_node";
	
	String FAILURE_LOG_BANK="bank_failure_log";
	String FAILURE_LOG_LOAN="loan_failure_log";
	String FAILURE_LOG_CREDIT="credit_failure_log";
	String FAILURE_LOG_STOCK="stock_failure_log";
	String FAILURE_LOG_HOLDING="holding_failure_log";
	String FAILURE_LOG_TRANSACTION="transaction_failure_log";
	
	
	String HBASE_DELETE="delete-hbasedata";
	
	
	String LOGS_PREVIOUS_DATE="LOGS_PREVIOUS_DATE";
	
	String LOGS_PREVIOUS_DATE_BANK="LOGS_PREVIOUS_DATE_BANK";
	String LOGS_PREVIOUS_DATE_CREDIT="LOGS_PREVIOUS_DATE_CREDIT";
	String LOGS_PREVIOUS_DATE_STOCK="LOGS_PREVIOUS_DATE_STOCK";
	String LOGS_PREVIOUS_DATE_LOAN="LOGS_PREVIOUS_DATE_LOAN";
	String LOGS_PREVIOUS_DATE_HOLDING="LOGS_PREVIOUS_DATE_HOLDING";
	String LOGS_PREVIOUS_DATE_TRANSACTION="LOGS_PREVIOUS_DATE_TRANSACTION";
	String LOGS_PREVIOUS_DATE_ALL="LOGS_PREVIOUS_DATE_ALL";
	String CURRENT_DATE="CURRENT_DATE";
	
	String CONTAINER_BANK="bank";
	String CONTAINER_LOAN="loan";
	String CONTAINER_STOCK="stock";
	String CONTAINER_CREDIT="credit";
	String CONTAINER_HOLDING="holding";
	String CONTAINER_TRANSACTION="transaction";
	
	String BANK_OUTPUT="bankresult";
	String LOAN_OUTPUT="loanresult";
	String STOCK_OUTPUT="stockresult";
	String CREDIT_OUTPUT="creditresult";
	String HOLDING_OUTPUT="holdingresult";
	String TRANSACTION_OUTPUT="transactionresult";
	
	String EXTRACT_OUTPUT_PATH="extractOutputPath";
	
	String WORKFLOW_STATUS="workflow_status";
	String WORKFLOW_LOG_UPDATE_DATE="workflow_log_update_date";
	String WORKFLOW_ERROR_NODE="workflow_error_node";
	String BANK_LOG_UPDATE_DATE="bank_log_update_date";
	String BANK_LOG_STATUS="bank_log_status";
	String CREDIT_LOG_UPDATE_DATE="credit_log_update_date";
	String CREDIT_LOG_STATUS="credit_log_status";
	String LOAN_LOG_UPDATE_DATE="loan_log_update_date";
	String LOAN_LOG_STATUS="loan_log_status";
	String STOCK_LOG_UPDATE_DATE="stock_log_update_date";
	String STOCK_LOG_STATUS="stock_log_status";
	String HOLDING_LOG_UPDATE_DATE="holding_log_update_date";
	String HOLDING_LOG_STATUS="holding_log_status";
	String TRANSACTION_LOG_UPDATE_DATE="transaction_log_update_date";
	String TRANSACTION_LOG_STATUS="transaction_log_status";
	
	String BANK_LAST_SUCCESS_DATE="bank_last_success_date";
	String CREDIT_LAST_SUCCESS_DATE="credit_last_success_date";
	String LOAN_LAST_SUCCESS_DATE="loan_last_success_date";
	String STOCK_LAST_SUCCESS_DATE="stock_last_success_date";
	String HOLDING_LAST_SUCCESS_DATE="holding_last_success_date";
	String TRANSACTION_LAST_SUCCESS_DATE="transaction_last_success_date";
	String ALL_INGESTION_LAST_SUCCESS_DATE="all_ingestion_last_success_date";
	
	String CUSTOM_DATE_VAL="None";
	String JUICE_KEY="juice_key";
	
	String ARCHIEVETIME = "archieveTime";
	String DATA_DELIMITER = "data.delimiter.customer";
	String CUSTOMER_RESETTING_SCHEMA = "customer.resting.schema";
	String MODEL_INPUT_SCHEMA = "model.input.schema";
	String DATA_DELIMITER_COMMA = "data.delimiter";
	
}
