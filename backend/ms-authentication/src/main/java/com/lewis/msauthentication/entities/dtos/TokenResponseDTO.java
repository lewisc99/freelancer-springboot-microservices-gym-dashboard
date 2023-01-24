package com.lewis.msauthentication.entities.dtos;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TokenResponseDTO {

    private String format = "JWT";

    private  String token;

    private String created;

    private String expirationToken;

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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getExpirationToken() {
        return expirationToken;
    }

    public void setExpirationToken(String expirationToken) {
        this.expirationToken = expirationToken;
    }
}
