package com.ticket.ws.kullanici;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KullaniciService {

    private final KullaniciRepository kullaniciRepository;


    public void save(Kullanici kullanici) {
        kullaniciRepository.save(kullanici);
    }

    List<Kullanici>kullaniciList(){
       return kullaniciRepository.findAll();
    }


    public Kullanici getByEposta(String eposta) {
        Kullanici inDB=kullaniciRepository.findByEposta(eposta);
        if(inDB==null){
            return null;
        }
        return inDB;
    }
}
