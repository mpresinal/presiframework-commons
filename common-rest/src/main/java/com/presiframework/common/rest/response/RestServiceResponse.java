/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presiframework.common.rest.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.MediaType;

/**
 * Esta clase representa la estructura de la respuesta de cualquier servicio
 * rest
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @param <P> payload
 * @since 1.0
 */
public class RestServiceResponse<P> implements Serializable {

    private static final long serialVersionUID = 5835442589349454044L;

    private static final String OBJECT_NAME = RestServiceResponse.class.getSimpleName();
    
    private ResponseStatus status;
    private P data;
    private List<ErrorMessage> errors;

    public RestServiceResponse() {
    }

    public RestServiceResponse(ResponseStatus status) {
        this.status = status;
    }

    public RestServiceResponse(ResponseStatus status, P data) {
        this(status, data, null);
    }

    public RestServiceResponse(ResponseStatus status, P data, List<ErrorMessage> errors) {
        this.status = status;
        this.data = data;
        this.errors = errors;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    public P getData() {
        return data;
    }

    public void setData(P data) {
        this.data = data;
    }

    public List<ErrorMessage> getErrors() {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        return errors;
    }

    public void setErrors(List<ErrorMessage> errors) {
        this.errors = errors;
    }

    public String get_objectName() {
        return OBJECT_NAME;
    }

    public String toJson(boolean dataAsObject) {
        StringBuilder buffer = new StringBuilder("{");
        buffer.append("\"_objectName\":").append("\"").append(get_objectName()).append("\",")
                .append("\"status\":").append("\"").append(status).append("\",")
                .append("\"data\":");

        if (dataAsObject) {
            buffer.append(data).append(",");

        } else {
            buffer.append(data != null ? "\"" + data + "\"" : null).append(",");
        }

        buffer.append("\"errors\": [");

        int index = 1;
        int size = getErrors().size();

        getErrors().forEach(e -> {
            buffer.append(e.toJson());
            if (index < size) {
                buffer.append(",");
            }
        });

        buffer.append("] }");

        return buffer.toString();
    }

    public String toXml() {
        StringBuilder buffer = new StringBuilder("<Response>");
        buffer.append("<Status>").append(status).append("</Status>");
        buffer.append("<Data/>");

        buffer.append("<Errors>");
        getErrors().forEach(e -> {
            buffer.append(e.toXml());
        });
        buffer.append("</Errors>");
        buffer.append("</Response>");

        return buffer.toString();
    }

    public String toMediaType(MediaType mediaType) {

        if ((MediaType.APPLICATION_JSON == mediaType) || (MediaType.APPLICATION_JSON_UTF8 == mediaType)) {
            return toJson(true);
        }

        if ((MediaType.TEXT_XML == mediaType) || (MediaType.TEXT_HTML == mediaType)) {
            return toXml();
        }

        return null;
    }

    @Override
    public String toString() {
        return "RestServiceResponse{" + "status=" + status + ", data=" + data + ", errors=" + errors + '}';
    }

}
