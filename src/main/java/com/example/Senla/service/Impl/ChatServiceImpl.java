package com.example.Senla.service.Impl;

import java.util.ArrayList;
import java.util.List;

import com.example.Senla.dto.MessageDTO;
import com.example.Senla.entity.Messages;
import com.example.Senla.entity.Person;
import com.example.Senla.mapper.MessageMapper;
import com.example.Senla.repository.MessagesRepo;
import com.example.Senla.repository.PersonRepo;
import com.example.Senla.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ilyas Nigamatullin
 */
@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {

  Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);

  @Autowired
  private final MessagesRepo messagesRepo;
  @Autowired
  private final MessageMapper messageMapper;
  @Autowired
  private final PersonRepo personRepo;
  @Override
  public List<MessageDTO> getMessagesByTwoPerson(String createPerson, int recipientPersonId) {
    Person person = personRepo.findByUsername(createPerson).get();
    logger.info("Chat with 2 persons");
    List<Messages> messagesList = messagesRepo.findChat(person.getId(), recipientPersonId);
    if (messagesList != null) {
      for (Messages messages : messagesList) {
        if (messages.getRecipientPersonId() == recipientPersonId) {
          messages.setIsRead(true);
          messagesRepo.save(messages);
        }
      }
      return messageMapper.toListDTO(messagesList);
    }
    else {
      return new ArrayList<>();
    }
  }
}
