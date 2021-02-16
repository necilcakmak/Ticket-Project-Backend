package com.ticket.ws.kullanici;

import org.springframework.data.jpa.repository.JpaRepository;


public interface KullaniciRepository extends JpaRepository<Kullanici,Long> {

    Kullanici findByEposta(String eposta);


}
