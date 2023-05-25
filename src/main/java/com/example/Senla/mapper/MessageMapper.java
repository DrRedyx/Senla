package com.example.Senla.mapper;

import java.util.List;

import com.example.Senla.dto.MessageDTO;
import com.example.Senla.dto.SendMessageDTO;
import com.example.Senla.entity.Messages;
import org.mapstruct.Mapper;
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
