package com.presiframework.common.rest.response;

import java.io.Serializable;

/**
 * Esta clase es la representacion de un error de servicio.
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
