package com.ticket.ws.auth;


import com.ticket.ws.kullanici.Kullanici;
import com.ticket.ws.kullanici.KullaniciDTO;
import com.ticket.ws.kullanici.KullaniciRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthController {

    @Autowired
    KullaniciRepository kullaniciRepository;



    @PostMapping("/api/auth")
    ResponseAuthModel handleAuthentication(@RequestBody LoginModel loginModel) throws Exception {
        Kullanici kullanici=kullaniciRepository.findByEposta(loginModel.getEposta());
        ModelMapper modelMapper=new ModelMapper();

        if(kullanici!=null){
            if(kullanici.getParola().equals(loginModel.getParola())){
                String token = getJWTToken(kullanici);
                ResponseAuthModel responseAuthModel = new ResponseAuthModel();
                responseAuthModel.setKullanici(modelMapper.map(kullanici, KullaniciDTO.class));
                responseAuthModel.setToken(token);
                

                return responseAuthModel;
            }
            throw new Exception("Parola Yanlış.");
        }
        throw new Exception("Kullanıcı Adı Yanlış.");

    }

    private String getJWTToken(Kullanici kullanici) {
        String secretKey = "necil";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(kullanici.getRol());

        String token = Jwts
                .builder()
                .setId("JWT")
                .setSubject(kullanici.getEposta())
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }

}
