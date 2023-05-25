package com.example.Senla.Controller;

import java.util.List;

import com.example.Senla.DTO.MessageDTO;
import com.example.Senla.DTO.SendMessageDTO;
import com.example.Senla.Service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Work with messages")
public class MessageController {

  @Autowired
  private final MessageService messageService;

  @PostMapping("{person_id}/send")
  @Operation(summary = "Send message to seller")
  public ResponseEntity<MessageDTO> sendMessage(@PathVariable("person_id") int personId,
      @RequestBody
      SendMessageDTO sendMessageDTO) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ResponseEntity.ok(
        messageService.sendMessage(authentication.getName(), personId, sendMessageDTO));
  }

  @GetMapping("/new")
  @Operation(summary = "Get my new messages")
  public ResponseEntity<List<MessageDTO>> getMyNewMessages() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ResponseEntity.ok(messageService.getAllMyNewMessages(authentication.getName()));
  }

  @GetMapping("/{message_id}")
  @Operation(summary = "Get 1 message")
  public ResponseEntity<MessageDTO> getMyMessage(@PathVariable("message_id") int messageId) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ResponseEntity.ok(messageService.getMessage(messageId, authentication.getName()));
  }
}
