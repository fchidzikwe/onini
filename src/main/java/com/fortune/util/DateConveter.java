package com.fortune.util;


import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by fortune on 7/11/17.
 */

public class DateConveter {




    public static Date stringToDate (String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = formatter.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date add(Date startDate, int durationtype, int duration) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(durationtype, duration);
        Date endDate = cal.getTime();
        return endDate;
    }
}