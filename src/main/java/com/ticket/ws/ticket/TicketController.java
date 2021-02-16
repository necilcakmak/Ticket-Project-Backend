package com.ticket.ws.ticket;

import com.ticket.ws.kullanici.Kullanici;
import com.ticket.ws.kullanici.KullaniciDTO;
import com.ticket.ws.kullanici.KullaniciRepository;
import com.ticket.ws.shared.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor

public class TicketController {

    @Autowired
    private final TicketService ticketService;

    @Autowired
    private final KullaniciRepository kullaniciRepository;

    @PostMapping("/api/ticketOlustur")
    public GenericResponse ticketOlustur(@Valid @RequestBody TicketDTO ticketDTO){
        ModelMapper modelMapper=new ModelMapper();
        KullaniciDTO inDb=modelMapper.map(kullaniciRepository.findById(ticketDTO.getKullaniciId()).get(),KullaniciDTO.class);
        if(ticketDTO.getId()==null){
            ticketDTO.setOAdSoyad(inDb.getAdSoyad());
            ticketDTO.setOTarihi(LocalDateTime.now());
        }

        Ticket ticket=modelMapper.map(ticketDTO,Ticket.class);
        ticket.setKullanici(modelMapper.map(inDb, Kullanici.class));
        ticketService.save(ticket);

        return new GenericResponse("ticket oluşturuldu");
    }
    @GetMapping("/api/ticket/{id}")
    public TicketDTO getTicketsById(@PathVariable Long id){
        return ticketService.findById(id);
    }
    @GetMapping("/api/ticketlar/{id}")
    public List<TicketDTO> getTicketsByKullaniciId(@PathVariable Long id){
        return ticketService.findOne(id);
    }


    @GetMapping("/api/ticketlar")
    public List<TicketDTO> getTickets(){
        return ticketService.getTickets();
    }

    @DeleteMapping("/ticketSil/{id}")
    void delete(@PathVariable Long id) {
        ticketService.delete(id);
    }

}
