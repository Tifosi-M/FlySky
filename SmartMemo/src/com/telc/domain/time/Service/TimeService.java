package com.telc.domain.time.Service;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeService {
	private static TimeService service=new TimeService();
	private TimeService(){
	
	}
	public static TimeService getInstance(){
		return service;
	}
	/**
	 * 
	 * @return 获取系统的当前时间，时间的格式为yyyy-MM-dd HH:mm:ss
	 */
	@SuppressLint("SimpleDateFormat")
	public String getCurrentTime(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		String string = simpleDateFormat.format(date);
		return string;
	}
	
	
	/**
	 * 
	 * @param m 毫秒数字
	 * @return 将毫秒数字转化为String类型的时间，格式为yyyy-MM-dd HH:mm:ss
	 */
	@SuppressLint("SimpleDateFormat")
	public String longSwithToString(long m){
		Date date = new Date(m);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String string = simpleDateFormat.format(date);
		return string;
	}
	
	//将时间字符串转化为long类型的毫秒数
	public long getSecondsFromDate(String expireDate){ 
        if(expireDate == null||expireDate.trim().equals(" "))
        	return 0; 
        Calendar   c   =   Calendar.getInstance(); 
        int   expireYear   =   Integer.parseInt(expireDate.substring(0,4)); 
        int   expireMonth   =   Integer.parseInt(expireDate.substring(5,7)); 
        int   expireDay   =   Integer.parseInt(expireDate.substring(8,10)); 
        int hou = Integer.parseInt(expireDate.substring(11, 13));
        int min = Integer.parseInt(expireDate.substring(14, 16));
        int sec = Integer.parseInt(expireDate.substring(17, 19));
        c.set(expireYear,   expireMonth-1,   expireDay,hou,min,sec); 
        long time1 = c.getTimeInMillis();
        return  time1;
} 
	
	/**
	 * 
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return 时间的间隔数字，为毫秒计算
	 */
	public static long getGapCount(Date startDate, Date endDate) {  
	       Calendar fromCalendar = Calendar.getInstance();    
	       fromCalendar.setTime(startDate);    
	       fromCalendar.set(Calendar.HOUR_OF_DAY, 0);    
	       fromCalendar.set(Calendar.MINUTE, 0);    
	       fromCalendar.set(Calendar.SECOND, 0);    
	       fromCalendar.set(Calendar.MILLISECOND, 0);    
	   
	       Calendar toCalendar = Calendar.getInstance();    
	       toCalendar.setTime(endDate);    
	       toCalendar.set(Calendar.HOUR_OF_DAY, 0);    
	       toCalendar.set(Calendar.MINUTE, 0);    
	       toCalendar.set(Calendar.SECOND, 0);    
	       toCalendar.set(Calendar.MILLISECOND, 0);    
	       return (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime());  
	}  
}
