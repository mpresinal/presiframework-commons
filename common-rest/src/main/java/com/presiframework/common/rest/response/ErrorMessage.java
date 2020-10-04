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

package com.presiframework.common.rest.response;

import java.io.Serializable;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public class ErrorMessage implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3757788496687030397L;

    private int errorCode;
    private String message;

    public ErrorMessage() {
    }

    public ErrorMessage(String message) {
        this.message = message;
    }

    public ErrorMessage(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toJson() {
        return "{\"errorCode\": " + errorCode + ", \"message\":" + (message != null? "\""+message+"\"" : null) + "}";
    }
    
    public String toXml() {
        return "<Error><ErrorCode>"+ errorCode + "</ErrorCode><Message>" + (message != null? message : "") + "</Message></Error>";
    }
    
    @Override
    public String toString() {
        return "Error{" + "errorCode=" + errorCode + ", message=" + message + '}';
    }    
}
