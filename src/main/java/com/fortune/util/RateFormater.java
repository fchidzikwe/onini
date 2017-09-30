package com.fortune.util;

/**
 * @author fchidzikwe on 9/16/17
 */
public class RateFormater {


    public static Long convertToMinutes(String time){

        String hoursInString = time.substring(0,2);
        String minutesInString = time.substring(3,5);
        String secondsInString = time.substring(6);

        Long hours = Long.valueOf(hoursInString).longValue() * 60;
        Long minutes = Long.valueOf(minutesInString).longValue();
        Long seconds = Long.valueOf(secondsInString).longValue() /60;

        return hours + minutes + seconds;
    }

}
