package com.bdp.test.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;


public class LogFileConcatMapper extends Mapper<LongWritable, Text, NullWritable, Text>{
	
	private static Logger logger = Logger.getLogger(LogFileConcatMapper.class);
	
	@Override
	public void setup(Mapper<LongWritable, Text, NullWritable, Text>.Context context) {
		logger.info("Intializing Mapper..");
	}
	
	public void map(LongWritable key, Text value, Mapper<LongWritable, Text, NullWritable, Text>.Context context) {
		
		logger.info("Inside Mapper..");
		
		String[] values = value.toString().split(",");
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<=values.length-1; i=i+2) {
			sb.append(values[i]).append("_").append(values[i+1]).append(",");
		}
		
		try {
			context.write(null, new Text(sb.length() > 0 ? sb.substring(0,sb.length()-1) : ""));
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		}
	}
}
