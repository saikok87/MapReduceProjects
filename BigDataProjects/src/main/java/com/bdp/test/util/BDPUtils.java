package com.bdp.test.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;

import com.bdp.test.constants.BDPConstants;

public class BDPUtils implements BDPConstants{

	private static Logger logger = Logger.getLogger(BDPUtils.class);
	private static Properties properties = null;
	private static final String[] formats = {
		 "yyyy-MM-dd HH:mm:ss.SSS", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSSS",
         "yyyyMMdd", "yyyy-MM-dd" 
	};
	
	public static Properties loadProperties(String configFile, FileSystem fs) {
		
			logger.info("Inside loadProperties()");
			try {
				if (properties == null) {
					properties = new Properties();
					Path inFile = new Path(configFile);
					FSDataInputStream fis = fs.open(inFile);
					properties.load(fis);
					logger.info("config file loaded successfully");
				}
				else{
					Path inFile =  new Path(configFile);
					FSDataInputStream fis = fs.open(inFile);
					properties.load(fis);
					logger.info("config file loaded successfully");
				}
				
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				String msg = "Error reading the properties file "+configFile+" make sure the properties file exists "
						+ "with proper permissions and try again";
				logger.error(msg);
			}
		
		return properties;
	}
	
	/**
	 * This method will read hive query file and return String.
	 * 
	 * @param filePath - Hive query file path.
	 * 
	 * @return String - Query file content.
	 * 
	 *
	 * 
	 */
	public static String readQueryFile(String filePath){
		
		Path path = new Path(filePath);
		String line = "";
		BufferedReader br = null;
		StringBuffer sb = null;
		
		try {
			FileSystem fs = FileSystem.get(new Configuration());
			
			if(fs.exists(path)){
				br = new BufferedReader(new InputStreamReader(fs.open(path)));
				while((line = br.readLine()) != null){
					/*if (line.endsWith(";"))
					    line = line.substring(0, line.length() - 1);*/
					if(sb == null)
						sb = new StringBuffer(line.trim());
					else
						sb.append(SPACE).append(line.trim());
				}
			}
		} catch (IOException e) {
			logger.error("File not found " + e);
		}
		finally{
			if(br != null)
				try {
					br.close();
				} catch (IOException e) {
					logger.error("IOException occured while reading query file", e);
				}
			
		}
		return sb.toString().trim(); 
	}
	
	/***
	 * Method to convert string to date object
	 * 
	 * @param date1
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date convertStringToDate(String date1) {
		Date d1=null;
		if(date1!=null && !date1.equals("\\N") && date1.length() > 0) {
			for(String parse : formats) {
				SimpleDateFormat sdf = new SimpleDateFormat(parse);
				
				try {
					d1=sdf.parse(date1);
					break;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
		return d1;
		
	}
	
}
