package com.ticket.ws.kullanici;

import com.ticket.ws.ticket.Ticket;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Kullanici {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Size(max = 50)
    private String adSoyad;

    @NotNull
    private String rol="ROLE_USER";

    @NotNull
    @Size(max = 11)
    private String telNo;

    @NotNull

    @Size(max = 100)
    private String eposta;

    @NotNull
    @Size(min = 8,max = 32)
    //@Pattern(regexp = "^(?=.*[a-z])(?=.*\\d).*$")
    private String parola;


    @OneToMany(mappedBy = "kullanici",cascade = CascadeType.ALL,orphanRemoval = true )
    private List<Ticket> ticketList=new ArrayList<>();

}
