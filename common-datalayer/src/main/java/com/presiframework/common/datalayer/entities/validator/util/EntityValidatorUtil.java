/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presiframework.common.datalayer.entities.validator.util;

import com.presiframework.common.datalayer.entities.exceptions.RequiredFieldException;
import com.presiframework.common.datalayer.entities.exceptions.ValueNotAllowedException;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public class EntityValidatorUtil {

    public static void validateNotNull(Object value) throws RequiredFieldException {
        validateNotNull(value, "value");
    }

    public static void validateNotNull(Object value, String fieldName) throws RequiredFieldException {
        if (value == null) {
            throw new RequiredFieldException(fieldName);
        }
    }

    public static void validateNotEmpty(String value) throws RequiredFieldException {
        validateNotEmpty((Object) value, "value");
    }

    public static void validateNotEmpty(String value, String fieldName) throws RequiredFieldException {
        validateNotEmpty((Object) value, fieldName);
    }

    public static void validateNotEmpty(Collection value) throws RequiredFieldException {
        validateNotEmpty((Object) value, "value");
    }

    public static void validateNotEmpty(Collection value, String fieldName) throws RequiredFieldException {
        validateNotEmpty((Object) value, fieldName);
    }

    public static void validateNotNullNotEmpty(Object value, String fieldName) throws RequiredFieldException {
        validateNotNull(value, fieldName);
        validateNotEmpty(value, fieldName);
    }

    private static void validateNotEmpty(Object value, String fieldName) throws RequiredFieldException {
        boolean datoempty = false;
        if (value instanceof String) {
            datoempty = ((String) value).trim().isEmpty();

        } else if (value instanceof Collection) {
            datoempty = ((Collection) value).isEmpty();
        }

        if (datoempty) {
            throw new RequiredFieldException(fieldName);
        }
    }

    public static boolean validateNeitherNullNorEmpty(String value) {
        return value != null && !value.isEmpty();
    }

    public static boolean validateNeitherNullNorEmpty(Collection value) {
        return value != null && !value.isEmpty();
    }

    public static boolean validateNeitherNullNorEmpty(String... valuees) {
        for (String val : valuees) {
            if (!validateNeitherNullNorEmpty(val)) {
                return false;
            }
        }
        return true;
    }

    public static void validateAllowedValue(String value, String... allowedValues) throws ValueNotAllowedException {
        boolean valido = false;
        for (String val : allowedValues) {
            if (val.equals(value)) {
                valido = true;
            }
        }

        if (!valido) {
            throw new ValueNotAllowedException(value, (Object[]) allowedValues);
        }
    }

    public static boolean validateCumpleRegexPattern(String regex, String value) {
        Matcher matcher = Pattern.compile(regex).matcher(value);
        return matcher.matches();
    }

}
