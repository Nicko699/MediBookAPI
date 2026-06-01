package org.medibook.Exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorMessage {
    private Integer typeError;
    private HttpStatus status;
    private String message;
    private LocalDateTime date;

    public ErrorMessage() {
    }

    public ErrorMessage(Integer typeError, HttpStatus status, String message, LocalDateTime date) {
        this.typeError = typeError;
        this.status = status;
        this.message = message;
        this.date = date;
    }

    public Integer getTypeError() {
        return typeError;
    }

    public void setTypeError(Integer typeError) {
        this.typeError = typeError;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
