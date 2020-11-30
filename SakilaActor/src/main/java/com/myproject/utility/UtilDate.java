package com.myproject.utility;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UtilDate {

	public static Timestamp convertStringToTimestamp(String dateString) {
		try {

			java.util.Date utilDate = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			Timestamp ts = new Timestamp(sqlDate.getTime());

			return ts;
		} catch (ParseException e) {
			System.out.println("Exception :" + e);
			return null;
		}
	}
}
