/*
 * Copyright 2020 Miguel Presinal.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.presiframework.common.rest.handler.exception;

import com.presiframework.common.datalayer.entities.exceptions.AbstractFieldException;
import com.presiframework.common.datalayer.entities.exceptions.BaseException;
import com.presiframework.common.datalayer.entities.exceptions.BeanValidationException;
import com.presiframework.common.datalayer.entities.exceptions.NoDataFoundException;
import com.presiframework.common.rest.response.ErrorMessage;
import com.presiframework.common.rest.response.ValidationErrorMessage;
import com.presiframework.common.service.exception.InternalServiceException;
import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com.do>
 * @since 1.0
 */
public class ExceptionHandler {

    private static ExceptionHandler instance;

    private ExceptionHandler() {
    }

    public static ExceptionHandler getInstance() {
        if (instance == null) {
            instance = new ExceptionHandler();
        }
        return instance;
    }

    public HttpStatus getStatusCodeForException(Exception e) {
        int exceptionCode = -1;
        if (e instanceof BaseException) {
            exceptionCode = ((BaseException) e).getCode();
        }

        switch (exceptionCode) {
            case -1:
            case InternalServiceException.CODE:
                return HttpStatus.INTERNAL_SERVER_ERROR;

            case NoDataFoundException.CODE:
                return HttpStatus.NOT_FOUND;

            default:
                return HttpStatus.BAD_REQUEST;

        }
    }

    public List<ErrorMessage> extractErrorMessagesFromException(Exception e) {
        List<ErrorMessage> list = new ArrayList<>();

        if (e instanceof BeanValidationException) {
            BeanValidationException ex = (BeanValidationException) e;            
            ex.getViolations().forEach(v -> {
                ConstraintViolation cv = (ConstraintViolation) v;
                list.add(new ValidationErrorMessage(ex.getCode(), cv.getPropertyPath().iterator().next().getName(), cv.getMessage()));
            });
            
        } else
        if (e instanceof AbstractFieldException) {
            AbstractFieldException ex = (AbstractFieldException) e;

            if (ex.getFieldName() != null) {
                list.add(new ValidationErrorMessage(ex.getCode(), ex.getFieldName(), ex.getMessage()));
            } else {
                list.add(new ErrorMessage(ex.getCode(), ex.getMessage()));
            }

        } else if (e instanceof BaseException) {
            BaseException ex = (BaseException) e;
            list.add(new ErrorMessage(ex.getCode(), ex.getMessage()));

        } else {
            list.add(new ErrorMessage(InternalServiceException.CODE, InternalServiceException.DESCRIPTION));
        }

        return list;
    }
}
