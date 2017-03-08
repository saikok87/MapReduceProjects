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

public class LogFileConcatDriver extends Configured implements Tool, BDPConstants {
	
	private static Logger logger = Logger.getLogger(LogFileConcatDriver.class);
	private Configuration conf;
	private static FileSystem fs;
	
	public static void main(String[] args) {
		try {
			logger.info("Inside main method.....");
			int exit = ToolRunner.run(new LogFileConcatDriver(), args);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	public int run(String[] args) throws Exception {
		
		logger.info("Inside run() method......");
		
		String inputDataSet1 = args[0];
		String outputDataset1 = args[1];
		String configFile1 = args[2];
		
		//set configuration properties
		conf = getConf();
		
		//get hold of hadoop file system
		fs = FileSystem.get(conf);
		
		// validate input metadata
		Path path = new Path(inputDataSet1);
		if(!fs.exists(path)) {
			logger.warn("Input directory " + path + " does not exist");
			return -1;
		}
		
		// load the properties file
		Properties props = BDPUtils.loadProperties(configFile1, fs);
		
		conf.set(MAPRED_JOB_QUEUE_NAME, props.getProperty(MAPRED_JOB_QUEUE_NAME));
		conf.set(OUTPUT_PATH, outputDataset1);
		
		Job job = new Job(conf, "MR: Concatenating 2 adjacent columns in log file....");
		job.setJarByClass(LogFileConcatDriver.class);
		job.setMapperClass(LogFileConcatMapper.class);
		//job.setReducerClass(ProductPriceTop10VerReducer.class);
		job.setNumReduceTasks(0);
        /*job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(Text.class);
        */
		job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);
		
        logger.info("Input Path to the map-reduce job - " + inputDataSet1);
		FileInputFormat.addInputPath(job, path);
		
		logger.info("Output Path to the map-reduce job - " + outputDataset1);
		Path output = new Path(outputDataset1);
		FileOutputFormat.setOutputPath(job, output);
		
		// delete output if existing
		logger.info("Deleting output path " + outputDataset1);
		FileSystem.get(conf).delete(output,true);		
		
		return (job.waitForCompletion(true) ? 0 : 1);
	
	}

}
