package com.example.Senla.Service;

import java.util.List;

import com.example.Senla.DTO.MessageDTO;
import com.example.Senla.DTO.SendMessageDTO;

/**
 * @author Ilyas Nigamatullin
 */
public interface MessageService {

  MessageDTO sendMessage(String createPerson, int recipientPersonId, SendMessageDTO sendMessageDTO);

  List<MessageDTO> getAllMyNewMessages(String username);

  MessageDTO getMessage(int id, String username);
}
