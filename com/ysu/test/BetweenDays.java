package com.ys.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BetweenDays {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
//		Date birthDay = sdf.parse("1986-12-06 10:35:00");
		Date birthDay = sdf.parse("2014-08-29 10:35:00");
//		Date birthDay = sdf.parse("2014-10-10 10:35:00");
		Date today = new Date();
		betweenDays(birthDay,today);
	}
	
	public static void betweenDays(Date d1,Date d2)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(d1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(d2);
		long time2 = cal.getTimeInMillis();
		long seconds = (time2 - time1)/1000;
		long days = seconds / (24 * 60 * 60);
		long hours = seconds % (24 * 60 * 60)/3600;
		long secs = seconds % 60;
		
		System.out.println(days + " ÃÏ:" + hours + " –° ±:" + secs + " √Î");
		System.out.println((time2 - time1));
		System.out.println((time2 - time1)/(60 * 60 * 1000));
		long day = (time2 - time1)/(1000 * 60 * 60 *24);
		int betweendays = Integer.parseInt(String.valueOf(day));
		System.out.println("Between days:"+ betweendays);
	}
}
