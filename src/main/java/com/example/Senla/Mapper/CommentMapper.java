package com.example.Senla.Mapper;

import java.util.List;

import com.example.Senla.DTO.AddCommentDTO;
import com.example.Senla.DTO.CommentDTO;
import com.example.Senla.Entity.Comments;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

/**
 * @author Ilyas Nigamatullin
 */
@Mapper(componentModel = "spring")
@Service
public interface CommentMapper {

  Comments toEntity(AddCommentDTO commentDTO);

  CommentDTO toDTO(Comments comments);

  List<CommentDTO> toListDTO(List<Comments> commentsList);
}
