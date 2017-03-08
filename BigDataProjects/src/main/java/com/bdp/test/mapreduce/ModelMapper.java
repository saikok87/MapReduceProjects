package com.bdp.test.mapreduce;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

import com.bdp.test.constants.BDPConstants;

public class ModelMapper extends Mapper<LongWritable, Text, NullWritable, Text> implements BDPConstants {
	
	private static Logger log = Logger.getLogger(ModelMapper.class);
	private String delimiter;
	private Map<String, Integer> schemaMap = new LinkedHashMap<String, Integer>();
	private Properties props;
	private String schema;
	
	protected void setup(Mapper<LongWritable, Text, NullWritable, Text>.Context context) {
		int i = 0;
		Configuration conf = context.getConfiguration();
		delimiter = conf.get(DATA_DELIMITER_COMMA);
		schema = conf.get(MODEL_INPUT_SCHEMA);
		
		String[] schemaArr = schema.split(delimiter);
		
		for(String str : schemaArr) {
			schemaMap.put(str.trim().toString(), i);
			i++;
		}
		
	}
	
	protected void map(LongWritable key, Text value,
			Mapper<LongWritable, Text, NullWritable, Text>.Context context) {
		
		log.info("Entering the Mapper Method !!");
		String val = value.toString();
		
		try {
			String[] valArr = val.split(delimiter);
			
			if(valArr.length == schemaMap.size()) {
				
				StringBuffer sb = new StringBuffer();
				String[] schemaArr = schema.split(delimiter);
				
				String prefix = "";
				for(String str : schemaArr) {
					sb.append(prefix);
					prefix=delimiter;
					sb.append(valArr[schemaMap.get(str.trim().toString())]);
				}
				context.write(NullWritable.get(),
						new Text(sb.toString().trim()));
				
				/*context.write(NullWritable.get(),
						new Text(sb.length() > 0 ? sb.substring(0,sb.length()-1) : ""));*/
				
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
