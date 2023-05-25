package com.example.Senla.service;

import java.util.List;

import com.example.Senla.dto.MessageDTO;
import com.example.Senla.dto.SendMessageDTO;

/**
 * @author Ilyas Nigamatullin
 */
public interface MessageService {

  MessageDTO sendMessage(String createPerson, int recipientPersonId, SendMessageDTO sendMessageDTO);

  List<MessageDTO> getAllMyNewMessages(String username);

  MessageDTO getMessage(int id, String username);
}
