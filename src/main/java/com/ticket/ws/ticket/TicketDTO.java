package com.ticket.ws.ticket;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ticket.ws.kullanici.Kullanici;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {

    private Long id;
    private String konu;
    private LocalDateTime oTarihi;
    private Integer oncelikSeviyesi;
    private Integer durum;
    private String detay;
    private String oAdSoyad;

    private Long kullaniciId;

    public void setKullanici(Kullanici kullanici){
        this.kullaniciId=kullanici.getId();

    }


}
