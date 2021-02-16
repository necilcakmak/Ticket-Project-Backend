package com.ticket.ws.ticket;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ticket.ws.kullanici.Kullanici;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(max = 256)
    private String konu;

    @NotNull
    private LocalDateTime oTarihi;

    @NotNull
    private Integer oncelikSeviyesi;

    @NotNull
    private Integer durum;

    @NotNull
    @Size(max = 10565)
    private String detay;

    @NotNull
    @Size(max = 256)
    private String oAdSoyad;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "KULLANICI_ID", referencedColumnName = "id")
    private Kullanici kullanici;


}
