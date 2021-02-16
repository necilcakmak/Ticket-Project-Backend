package com.ticket.ws.kullanici;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KullaniciDTO {
    private long id;
    private String adSoyad;
    private String rol;
    private String telNo;
    private String eposta;
    private String parola;
}
