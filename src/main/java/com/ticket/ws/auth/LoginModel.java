package com.ticket.ws.auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginModel implements Serializable {

    public String eposta;
    public String parola;
    public String adSoyad;
    public String id;
}
