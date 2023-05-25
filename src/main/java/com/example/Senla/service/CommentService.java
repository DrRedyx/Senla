package com.example.Senla.service;

import java.util.List;

import com.example.Senla.dto.AddCommentDTO;
import com.example.Senla.dto.CommentDTO;

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
