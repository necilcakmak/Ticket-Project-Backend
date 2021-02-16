package com.ticket.ws.ticket;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketMapper extends BaseConverter<Ticket, TicketDTO> {
}
