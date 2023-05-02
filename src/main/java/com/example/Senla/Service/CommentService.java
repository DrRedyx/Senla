package com.example.Senla.Service;

import java.util.List;

import com.example.Senla.DTO.AddCommentDTO;
import com.example.Senla.DTO.CommentDTO;

/**
 * @author Ilyas Nigamatullin
 */
public interface CommentService {

  CommentDTO addComment(int advertId, AddCommentDTO addCommentDTO, String username);

  CommentDTO updateComment(int commentId, AddCommentDTO addCommentDTO, String username);

  List<CommentDTO> getAllMyComments(String username);

  List<CommentDTO> getAllAdvertComments(int advertId);

  void deleteComment(int commentId, String username);




}
