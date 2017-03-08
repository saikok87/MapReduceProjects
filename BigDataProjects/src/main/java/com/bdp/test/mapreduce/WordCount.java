package com.bdp.test.mapreduce;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
//import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import com.bdp.test.mapreduce.WordCountMap;
import com.bdp.test.mapreduce.WordCountReduce;

public class WordCount {
        
 public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    conf.set("mapreduce.job.queuename", "yodlee");
        
    Job job = new Job(conf, "wordcount");
    
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    job.setJarByClass(WordCount.class);    
    job.setMapperClass(WordCountMap.class);
    job.setNumReduceTasks(0);
    //job.setReducerClass(WordCountReduce.class);
        
    job.setInputFormatClass(NLinesInputFormat.class); //custom input format
    job.setOutputFormatClass(TextOutputFormat.class);
        
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
    job.waitForCompletion(true);
 }
        
}
