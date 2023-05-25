package com.example.Senla.service.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.Senla.dto.MessageDTO;
import com.example.Senla.dto.SendMessageDTO;
import com.example.Senla.entity.Messages;
import com.example.Senla.entity.Person;
import com.example.Senla.exception.AccessDenied;
import com.example.Senla.mapper.MessageMapper;
import com.example.Senla.repository.MessagesRepo;
import com.example.Senla.repository.PersonRepo;
import com.example.Senla.service.MessageService;
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
public class MessageServiceImpl implements MessageService {

  Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
  @Autowired
  private final MessageMapper messageMapper;
  @Autowired
  private final MessagesRepo messagesRepo;
  @Autowired
  private final PersonRepo personRepo;

  @Override
  public MessageDTO sendMessage(String createPerson, int recipientPersonId,
      SendMessageDTO sendMessageDTO) {
    logger.info("Send message");
    Person person = personRepo.findByUsername(createPerson).get();
    Messages message = messageMapper.toEntity(sendMessageDTO);
    message.setCreateAt(LocalDateTime.now());
    message.setPerson(person);
    message.setRecipientPersonId(recipientPersonId);
    message.setIsRead(false);
    messagesRepo.save(message);
    return messageMapper.toDTO(message);
  }

  @Override
  public List<MessageDTO> getAllMyNewMessages(String username) {
    int recipientPersonId = personRepo.findByUsername(username).get().getId();
    List<Messages> newMessages = messagesRepo.findAllMyNewMessages(recipientPersonId);
    if (newMessages.isEmpty()) {
      logger.warn("Don`t new message");
      return new ArrayList<>();
    }
    else {
      logger.info("My new message");
      return messageMapper.toListDTO(newMessages);
    }
  }

  @Override
  public MessageDTO getMessage(int id, String username) {
    Messages messages = messagesRepo.findById(id).orElseThrow(RuntimeException::new);
    if (personRepo.findByUsername(username).get().getId() != messages.getRecipientPersonId()) {
      logger.error("It`s not your message");
      throw new AccessDenied("It`s not your message");
    }
    logger.info("Get and read message");
    messages.setIsRead(true);
    messagesRepo.save(messages);
    return messageMapper.toDTO(messages);
  }
}
