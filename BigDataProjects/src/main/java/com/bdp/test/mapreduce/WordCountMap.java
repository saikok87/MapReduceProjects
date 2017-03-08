package com.bdp.test.mapreduce;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMap extends Mapper<LongWritable, Text, Text, IntWritable> {
	    private final static IntWritable one = new IntWritable(1);
	    private Text word = new Text();
	        
	    /*public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
	        String line = value.toString();
	        StringTokenizer tokenizer = new StringTokenizer(line);
	        while (tokenizer.hasMoreTokens()) {
	            word.set(tokenizer.nextToken());
	            context.write(word, one);
	        }
	    }*/
	    
	    //to count lines using NLinesInputFormat
	    
	    public void map(LongWritable key, Text value,Context context) throws java.io.IOException ,InterruptedException
	    {
	        String lines = value.toString();
	        String []lineArr = lines.split("\n");
	        int lcount = lineArr.length;
	        context.write(new Text(new Integer(lcount).toString()),new IntWritable(1));
	     }
	 }
