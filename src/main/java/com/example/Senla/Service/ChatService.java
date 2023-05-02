package com.example.Senla.Service;

import java.util.List;

import com.example.Senla.DTO.MessageDTO;

/**
 * @author Ilyas Nigamatullin
 */
public interface ChatService {

  List<MessageDTO> getMessagesByTwoPerson(String createPerson, int recipientPersonId);
}
