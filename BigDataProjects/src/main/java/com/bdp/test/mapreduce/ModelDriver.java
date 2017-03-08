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

public class ModelDriver extends Configured implements Tool, BDPConstants {
	
	private Configuration conf;
	private static FileSystem fs;
	private static Logger log = Logger.getLogger(ModelDriver.class);
	
	public static void main(String[] args) {
		
		try {
			ModelDriver mDriver = new ModelDriver();
			long start = System.currentTimeMillis();
			int exit = ToolRunner.run(mDriver, args);
			long end = System.currentTimeMillis();
			log.info("Total time taken by the utility is: " + (end - start)
					+ " millisecs");
			
			if(exit != 0) {
				log.error("ModelDriver return code "
						+ exit);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public int run(String[] args) throws Exception {
		
		String inputPath = args[0];
		String outputPath = args[1];
		String configFile = args[2];
		String delimiter = args[3];
        
		// set the configuration properties
		conf = getConf();
		// get hold of hadoop file system
		fs = FileSystem.get(conf);
		
		// validate input metadata
		Path input = new Path(inputPath);
		if(!fs.exists(input)){
			log.warn("Input directory " + input + " does not exist");
			return -1;
		}
		
		Properties props = BDPUtils.loadProperties(configFile, fs);
		
		conf.set(MAPRED_JOB_QUEUE_NAME, props.getProperty(MAPRED_JOB_QUEUE_NAME));
		conf.set("model.config", configFile);
		conf.set(MODEL_INPUT_SCHEMA, props.getProperty(MODEL_INPUT_SCHEMA));
		conf.set(DATA_DELIMITER_COMMA, delimiter);
		
		Job job = new Job(conf,"Mapreduce: Model Driver");
		job.setJarByClass(ModelDriver.class);
		job.setMapperClass(ModelMapper.class);
		job.setMapOutputKeyClass(NullWritable.class);
		job.setMapOutputValueClass(Text.class);
		job.setNumReduceTasks(0);
		
		log.info("Input Path to the map-reduce job - " + inputPath);
		FileInputFormat.addInputPath(job, input);
		
		log.info("Ouput Path to the map-reduce job - " + outputPath);
		Path output = new Path(outputPath);

		FileOutputFormat.setOutputPath(job, output);
		
		// delete output if existing
		log.info("Deleting output path " + outputPath);
		FileSystem.get(conf).delete(new Path(outputPath), true);
		
		int status = (job.waitForCompletion(true) == true) ? 0 : 1;
		
		return status;
	}

}
