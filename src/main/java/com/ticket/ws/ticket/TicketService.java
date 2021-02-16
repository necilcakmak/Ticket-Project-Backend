package com.ticket.ws.ticket;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    List<TicketDTO> getTickets(){
        ModelMapper modelMapper=new ModelMapper();
        List<TicketDTO> asd = modelMapper.map(ticketRepository.findAll(),new TypeToken<List<TicketDTO>>() {}.getType());
        return asd;

    }

    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public void delete(Long id) {
        Ticket ticket=ticketRepository.findById(id).get();
        ticketRepository.delete(ticket);

    }


    public List<TicketDTO> findOne(Long id) {
        ModelMapper modelMapper=new ModelMapper();
        List<TicketDTO> asd = modelMapper.map(ticketRepository.findAllByKullaniciId(id),new TypeToken<List<TicketDTO>>() {}.getType());
        return asd;
    }
    public TicketDTO findById(Long id) {
        ModelMapper modelMapper=new ModelMapper();
        TicketDTO asd = modelMapper.map(ticketRepository.findById(id).get(),TicketDTO.class);
        return asd;
    }
}
