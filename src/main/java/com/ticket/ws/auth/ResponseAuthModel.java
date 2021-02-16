package com.ticket.ws.auth;

import com.ticket.ws.kullanici.KullaniciDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseAuthModel implements Serializable {

    private KullaniciDTO kullanici;
    private String token;
}
