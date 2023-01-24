package com.lewis.msauthentication.entities.dtos;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TokenResponseDTO {

    private String format = "JWT";

    private  String token;

    @DateTimeFormat(pattern="yyyy/dd/MM hh:mm")
    private Date created;

    @DateTimeFormat(pattern="yyyy/dd/MM hh:mm")
    private Date expirationToken;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getExpirationToken() {
        return expirationToken;
    }

    public void setExpirationToken(Date expirationToken) {
        this.expirationToken = expirationToken;
    }
}
