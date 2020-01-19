/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.presiframework.common.rest.util;

import com.presiframework.common.datalayer.entities.exceptions.ValueNotAllowedException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public class DataFormatUtil {

    public static final Locale LOCALE_ES = new Locale("es", "US");
    
    public static int parseInt(String number)throws ValueNotAllowedException {
       return parseNumber(number, null, Integer.class).intValue();
    }
    
    public static long parseLong(String number)throws ValueNotAllowedException {
       return parseNumber(number, null, Long.class).longValue();
    }
    
    public static short parseShort(String number)throws ValueNotAllowedException {
       return parseNumber(number, null, Short.class).shortValue();
    }
    
    public static byte parseByte(String number)throws ValueNotAllowedException {
       return parseNumber(number, null, Byte.class).byteValue();
    }
    
    public static double parseDouble(String number)throws ValueNotAllowedException {
       return parseNumber(number, null, Double.class).doubleValue();
    }
    
    public static float parseFloat(String number)throws ValueNotAllowedException {
       return parseNumber(number, null, Float.class).floatValue();
    }
    
    public static Date parseDate(String strDate, String pattern) throws ValueNotAllowedException {        
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.parse(strDate);
        } catch (ParseException ex) {
            throw new ValueNotAllowedException(strDate);
        }
    }
    
    public static Number parseNumber(String number, String pattern, Class returnType) throws ValueNotAllowedException {
        NumberFormat format = null;
        
        if(Double.class.equals(returnType) || Float.class.equals(returnType)) {            
            format = (pattern != null && !pattern.isEmpty())? new DecimalFormat(pattern) : new DecimalFormat();            
        } else {
            format = NumberFormat.getIntegerInstance();
        }
        
        try {
            return format.parse(number);
        } catch (ParseException ex) {
           throw new ValueNotAllowedException(number);
        }        
    }
    
    public static String formatDate(Date date, String pattern) {        
       return new SimpleDateFormat(pattern, LOCALE_ES).format(date);
    }

    
    public static interface FormatPattern {
        String DEFAULT_DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    }
}
