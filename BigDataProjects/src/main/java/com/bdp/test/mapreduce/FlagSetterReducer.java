package com.bdp.test.mapreduce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.log4j.Logger;

import com.bdp.test.constants.BDPConstants;

public class FlagSetterReducer extends Reducer<Text, Text, NullWritable, Text> implements BDPConstants{
	
	private static Logger log = Logger.getLogger(FlagSetterReducer.class);
	
	protected void setup(Context context) throws IOException,
			InterruptedException {
		Configuration conf = context.getConfiguration();
	}
	
	protected void reduce(Text key, Iterable<Text> values, Context context) {
		log.info("Inside CustomerRestingReducer ");
		String customerRestingFlag = "0";
		List<Map> monthFlagList = new ArrayList<Map>();
		
		try {
			for(Text value : values) {
				String[] valArr = value.toString().split(",");
				String month = valArr[2];
				Integer salary = Integer.parseInt(valArr[3]);
				Map<String, String> monthFlagMap = new HashMap<String, String>();
				
				if(salary >= 40000) {
					customerRestingFlag = "1";
				} else {
					customerRestingFlag = "0";
				}
				
				monthFlagMap.put(month, customerRestingFlag);
			    monthFlagList.add(monthFlagMap);
				
			}
			
			StringBuilder sb = new StringBuilder();
			
			for (Map s : monthFlagList)
			{
			    sb.append(s);
			    sb.append(",");
			}
			
			System.out.println(sb.toString());
			
			context.write(NullWritable.get(), new Text(new StringBuffer(key.toString().trim())
													   .append(" ").append(sb.toString()).toString()));
			
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
