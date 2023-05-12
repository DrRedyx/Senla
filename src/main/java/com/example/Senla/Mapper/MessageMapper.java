package com.example.Senla.Mapper;

import java.util.List;

import com.example.Senla.DTO.MessageDTO;
import com.example.Senla.DTO.SendMessageDTO;
import com.example.Senla.Entity.Messages;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

/**
 * @author Ilyas Nigamatullin
 */
@Mapper(componentModel = "spring")
@Component
public interface MessageMapper {

  Messages toEntity(SendMessageDTO sendMessageDTO);

  MessageDTO toDTO(Messages messages);

  List<MessageDTO> toListDTO(List<Messages> messagesList);
}
