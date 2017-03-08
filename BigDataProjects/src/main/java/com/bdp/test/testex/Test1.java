package com.bdp.test.testex;

public class Test1 {

	   public static void main(String args[]) {
	      String str1 = "CA";
	      String str2 = new String("NY");
	      String str3 = new String("MO");
	      
	      int result = str1.compareTo( str2 );
	      System.out.println(result);
	      
	      result = str2.compareTo( str3 );
	      System.out.println(result);
	   }
	}