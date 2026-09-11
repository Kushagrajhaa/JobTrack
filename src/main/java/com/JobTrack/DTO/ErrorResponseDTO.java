package com.JobTrack.DTO;

import java.time.LocalDateTime;

public class ErrorResponseDTO {
    private LocalDateTime timestamp;
    private int statusCode;
    private String errorCode;
    private String message;
    private String path;

    public ErrorResponseDTO(LocalDateTime timestamp, int statusCode, String errorCode, String message, String path) {
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
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
