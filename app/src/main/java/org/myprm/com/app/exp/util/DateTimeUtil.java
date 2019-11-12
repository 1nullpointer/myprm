package org.myprm.com.app.exp.util;

import android.util.Log;

import org.threeten.bp.LocalDate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Rakesh on 2/27/18.
 */

public class DateTimeUtil {

    public static final String TAG = "DateTimeUtil.class";

    public static final String DATE_FORMAT_1 = "EEE_HHmm";
    public static final String DATE_FORMAT_2 = "hh:mm a";

    public static final String DATE_FORMAT_3 = "hhmm";

    public static final String DATE_FORMAT_4 = "E, d MMM uuuu";

    public static final String DATE_FORMAT_5 = "d MMM";
    public static final String DATE_FORMAT_6 = "uuuu-MM-d";


    //TOday 00:00 hrs time in millis
    public static long getTodaysDateInMillis(){


        Calendar cal = Calendar.getInstance();
        int year  = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date  = cal.get(Calendar.DATE);
        cal.clear();
        cal.set(year, month, date);
        long todayMillis = cal.getTimeInMillis();

        return todayMillis;
    }

    public static String convertDateFormat(String fromDateFormat,String toDateFormat,String dateToConvert){

            String targetDateFormat = dateToConvert;

        try{

            DateFormat readFormat = new SimpleDateFormat(fromDateFormat, Locale.ENGLISH);
            Date parsedDate = readFormat.parse(dateToConvert);

            Log.d(TAG,"dateToConvert  " +parsedDate +" parsed Date "+ parsedDate);


            DateFormat writeFormat = new SimpleDateFormat(toDateFormat, Locale.ENGLISH);
            targetDateFormat = writeFormat.format(parsedDate);
            Log.d(TAG," targetDateFormat "+targetDateFormat);

            return targetDateFormat;


        }catch(ParseException pe){

            Log.e(TAG,"Exception while parsing date ",pe);
        }

        return targetDateFormat;

    }

    public static String dayOfWeek3Char(LocalDate selectedDate){
        return selectedDate.getDayOfWeek().toString().substring(0,1)+selectedDate.getDayOfWeek().toString().substring(1,3).toLowerCase();
    }

}
