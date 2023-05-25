package com.example.Senla.service;

import java.util.List;

import com.example.Senla.dto.MessageDTO;

/**
 * @author Ilyas Nigamatullin
 */
public interface ChatService {

  List<MessageDTO> getMessagesByTwoPerson(String createPerson, int recipientPersonId);
}
