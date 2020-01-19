/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.presiframework.common.rest.util;

import com.presiframework.common.datalayer.entities.exceptions.ValueNotAllowedException;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public class DateUtil {

    public static Date truncateDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
    
    public static Date setDateToMidNight(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }
    
    public static Date sustractDays(Date date, int days) {
        int tmpDays = days > 0? (days * -1) : days;
        return addOrSustractDays(date, tmpDays);
    }
    
    public static Date addDays(Date date, int days) {
        int tmpDays = days < 0? (days * -1) : days;
        return addOrSustractDays(date, tmpDays);
    }
    
    private  static Date addOrSustractDays(Date date, int days) {        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }
    
    public static Date addMilliseconds(Date date, long milliseconds) {
        long timeInMilli = date.getTime();        
        return new Date(timeInMilli + milliseconds);
    }
    
    public static Date parseAndSetToMidNight(String strDate, String pattern) throws ValueNotAllowedException {        
       return setDateToMidNight(DataFormatUtil.parseDate(strDate, pattern));
    }    
}
