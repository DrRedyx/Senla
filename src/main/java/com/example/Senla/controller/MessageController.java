package com.example.Senla.controller;

import java.util.List;

import com.example.Senla.dto.MessageDTO;
import com.example.Senla.dto.SendMessageDTO;
import com.example.Senla.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ilyas Nigamatullin
 */

@RestController
@RequestMapping("/messages")
@AllArgsConstructor
public class MessageController {

  @Autowired
  private final MessageService messageService;

  @PostMapping("{person_id}/send")
  public ResponseEntity<MessageDTO> sendMessage(@PathVariable("person_id") int personId,
      @RequestBody
      SendMessageDTO sendMessageDTO) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ResponseEntity.ok(
        messageService.sendMessage(authentication.getName(), personId, sendMessageDTO));
  }

  @GetMapping("/new")
  public ResponseEntity<List<MessageDTO>> getMyNewMessages() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ResponseEntity.ok(messageService.getAllMyNewMessages(authentication.getName()));
  }

  @GetMapping("/{message_id}")
  public ResponseEntity<MessageDTO> getMyMessage(@PathVariable("message_id") int messageId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ResponseEntity.ok(messageService.getMessage(messageId, authentication.getName()));
  }
}
