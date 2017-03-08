package com.bdp.test.mapreduce;

import java.util.Properties;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;

import com.bdp.test.constants.BDPConstants;
import com.bdp.test.util.BDPUtils;

public class FlagSetterDriver extends Configured implements Tool, BDPConstants {
	
	private Configuration conf;
	private static FileSystem fs;
	private static Logger log = Logger.getLogger(FlagSetterDriver.class);
	
	public static void main(String[] args) {
		try {
			int exit = ToolRunner.run(new FlagSetterDriver(), args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int run(String[] args) throws Exception {
		
		String inputDataset = args[0];
		String outputDataset = args[1];
		String delimiter = args[2];
		String configFile = args[3];
		
		// set the configuration properties
		conf = getConf();
		// get hold of hadoop file system
		fs = FileSystem.get(conf);

		// validate input metadata
		Path input = new Path(inputDataset);
		if (!fs.exists(input)) {
			log.warn("Input directory " + input + " does not exist");
			return -1;
		}
		
		// load the properties file
		Properties props = BDPUtils.loadProperties(configFile, fs);
				
		conf.set(MAPRED_JOB_QUEUE_NAME, props.getProperty(MAPRED_JOB_QUEUE_NAME));
		conf.set(DATA_DELIMITER, delimiter);
		conf.set(CUSTOMER_RESETTING_SCHEMA, props.getProperty(CUSTOMER_RESETTING_SCHEMA));		
		conf.set(OUTPUT_PATH, outputDataset);
		
		Job job = new Job(conf, "MR: Flag setter ");
		job.setJarByClass(FlagSetterDriver.class);
		job.setMapperClass(FlagSetterMapper.class);
		job.setReducerClass(FlagSetterReducer.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
		
		log.info("Input Path to the map-reduce job - " + inputDataset);
		FileInputFormat.addInputPath(job, input);
		
		log.info("Ouput Path to the map-reduce job - " + outputDataset);
		Path output = new Path(outputDataset);
		
		FileOutputFormat.setOutputPath(job, output);

		// delete output if existing
		log.info("Deleting output path " + outputDataset);
		FileSystem.get(conf).delete(new Path(outputDataset), true);
		
		int status = (job.waitForCompletion(true) == true) ? 0 : 1;
		
		return status;
	}

}
