package com.bdp.testcase;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.types.Pair;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.bdp.test.constants.BDPConstants;
import com.bdp.test.mapreduce.ModelMapper;

public class ModelMapperTest extends Configured implements BDPConstants{
	
	private String schema = "id,name";
	//private Configuration conf;
	MapDriver<LongWritable, Text, NullWritable, Text> mDriver;
	MapReduceDriver<LongWritable, Text, NullWritable, Text, NullWritable, Text> mrDriver;
	
	@Before
	public void setup() {
		ModelMapper modelMapper = new ModelMapper();
		mDriver = MapDriver.newMapDriver(modelMapper);
	}
	
	@Test
	public void testMapper() {
		try {
			Configuration conf = mDriver.getConfiguration();
			conf.set(MODEL_INPUT_SCHEMA, "id,name");
			conf.set(DATA_DELIMITER_COMMA, ",");
			mDriver.withInput(new LongWritable(), new Text("1,sai"));
			List<Pair<NullWritable, Text>> result = mDriver.run();
			
			assertNotNull(result);
			assertNotNull(result.get(0).getSecond().toString());
			assertEquals("1,sai", result.get(0).getSecond().toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
