package com.bdp.test.mapreduce;

import org.apache.log4j.Logger;
import java.io.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.conf.*;

public class ImageFilesDriver {
	private static Logger logger = Logger.getLogger(ImageFilesDriver.class);
	
	public static void main(String[] args) {
		int count = 0;
		try {
			FileSystem fs = FileSystem.get(new Configuration());
			boolean recursive = false;
			String pathStr = "/axp/rim/yodlee/dev/cuso_dev/sai/input";
			RemoteIterator<LocatedFileStatus> ri = fs.listFiles(new Path(pathStr), recursive);
			while (ri.hasNext()){
			    count++;
			    ri.next();
			}
			logger.info("No. of files in directory "+ pathStr +" : "+count);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
}