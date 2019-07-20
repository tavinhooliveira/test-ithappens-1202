package com.ithappens.utils;

import java.text.ParseException;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Years;


public class Utils {
			
	//Tempo de Task
	public String taskDateTempo(Date dateStart, Date dataStop) throws ParseException{
		
		DateTime dt1 = new DateTime(dateStart);
		DateTime dt2 = new DateTime(dataStop);

		String anos = null;
		String dias = null;
		String meses =null;
		try {
		
		anos = (Years.yearsBetween(dt1, dt2).getYears() + " Anos, ");
		dias = (Days.daysBetween(dt1, dt2).getDays() + " Dias, ");
		meses = (Months.monthsBetween(dt1, dt2).getMonths() + " Meses, ");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return anos+dias+meses;
	}
	
		

}
