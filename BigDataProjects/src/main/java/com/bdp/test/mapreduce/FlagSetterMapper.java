package com.bdp.test.mapreduce;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

import com.bdp.test.constants.BDPConstants;

public class FlagSetterMapper extends Mapper<LongWritable, Text, Text, Text> implements BDPConstants {
	private static Logger log = Logger.getLogger(FlagSetterMapper.class);
	private String delimiter;
	private Map<String, Integer> schemaMap = new HashMap<String, Integer>();
	
	@Override
	protected void setup(Mapper<LongWritable, Text, Text, Text>.Context context) {
		int i = 0;
		Configuration conf = context.getConfiguration();
		delimiter = conf.get(DATA_DELIMITER);
		String schema = conf.get(CUSTOMER_RESETTING_SCHEMA);
		String[] schemaArr = schema.split(",");
		
		for(String str : schemaArr) {
			schemaMap.put(str.toLowerCase(), i);
		}
	}
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context) {
		log.info("Entering the Mapper Method !!");
		String val = value.toString();
		
		String[] valArr = val.split(",");
		
		try {
			if(valArr.length == schemaMap.size()) {
				String id = valArr[0];
				String name = valArr[1];
				String month = valArr[2];
				String salary = valArr[3];
				
				val = new StringBuffer(id).append(",")
						.append(name).append(",")
						.append(month).append(",")
						.append(salary).toString();
						
				context.write(new Text(name.trim()), new Text(val.trim().toString()));		
						
			} else {
				log.info("Schema and Values for " + val + " do not match !!!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
