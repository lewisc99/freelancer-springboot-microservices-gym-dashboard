package com.lewis.msuser.config.exceptions;

import java.time.Instant;
import java.util.List;
public class ValidationError {

    private static final long serialVersionUID = 1L;

    private Instant timestamp;
    private Integer status;
    private List<String> error;
    private String message;
    private String path;

    public ValidationError() {
    }
    public ValidationError(Instant timestamp, Integer status, List<String> error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
