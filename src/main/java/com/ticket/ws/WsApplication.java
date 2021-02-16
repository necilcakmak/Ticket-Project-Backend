package com.ticket.ws;

import com.ticket.ws.auth.JWTAuthorizationFilter;
import com.ticket.ws.kullanici.Kullanici;
import com.ticket.ws.kullanici.KullaniciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
public class WsApplication implements ApplicationRunner {

    private final KullaniciRepository kullaniciRepository;

    public WsApplication(KullaniciRepository kullaniciRepository) {
        this.kullaniciRepository = kullaniciRepository;
    }



    public static void main(String[] args) {
        SpringApplication.run(WsApplication.class, args);
    }

    @EnableWebSecurity
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers(HttpMethod.POST, "/api/auth").permitAll()
                    .antMatchers(HttpMethod.POST, "/api/kayit").permitAll()
                    .anyRequest().authenticated();
        }
    }
    @Override
    public void run(ApplicationArguments args)throws Exception{
        Kullanici kullanici=new Kullanici();
        kullanici.setEposta("admin");
        kullanici.setParola("12345678");
        kullanici.setRol("ROLE_ADMIN");
        kullanici.setAdSoyad("Admin ADMİN");
        kullanici.setTelNo("1");
        kullaniciRepository.save(kullanici);
    }
}

