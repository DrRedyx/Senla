package com.example.Senla.mapper;

import java.util.List;

import com.example.Senla.dto.AddCommentDTO;
import com.example.Senla.dto.CommentDTO;
import com.example.Senla.entity.Comments;
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
