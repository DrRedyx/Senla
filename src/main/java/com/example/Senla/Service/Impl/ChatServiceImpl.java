package com.example.Senla.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import com.example.Senla.DTO.MessageDTO;
import com.example.Senla.Entity.Messages;
import com.example.Senla.Entity.Person;
import com.example.Senla.Mapper.MessageMapper;
import com.example.Senla.Repository.MessagesRepo;
import com.example.Senla.Repository.PersonRepo;
import com.example.Senla.Service.ChatService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional
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
