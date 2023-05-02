package com.example.Senla.Controller;

import java.util.List;

import com.example.Senla.DTO.MessageDTO;
import com.example.Senla.Service.ChatService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ilyas Nigamatullin
 */

@RestController
@RequestMapping("/chat")
@AllArgsConstructor
public class ChatController {

  @Autowired
  private final ChatService chatService;

  @GetMapping("/{recipient_person_id}")
  public ResponseEntity<List<MessageDTO>> getChat(@PathVariable("recipient_person_id") int recipientPersonId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ResponseEntity.ok(
        chatService.getMessagesByTwoPerson(authentication.getName(), recipientPersonId));
  }
}
