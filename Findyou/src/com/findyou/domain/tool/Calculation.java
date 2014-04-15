package com.findyou.domain.tool;

public class Calculation {
	
	public static void calculate(int sleepSeconds){
		try {
			Thread.sleep(sleepSeconds*1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
