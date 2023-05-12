package com.example.Senla.Service.Impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.Senla.DTO.AddCommentDTO;
import com.example.Senla.DTO.CommentDTO;
import com.example.Senla.Entity.Comments;
import com.example.Senla.Exception.AccessDenied;
import com.example.Senla.Exception.AdvertNotFoundException;
import com.example.Senla.Exception.UserNotFoundException;
import com.example.Senla.Mapper.CommentMapper;
import com.example.Senla.Repository.AdvertRepo;
import com.example.Senla.Repository.CommentsRepo;
import com.example.Senla.Repository.PersonRepo;
import com.example.Senla.Service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ilyas Nigamatullin
 */

@Service
public class CommentServiceImpl implements CommentService {

  Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);

  @Autowired
  private final CommentsRepo commentsRepo;

  @Autowired
  private final CommentMapper commentMapper;

  @Autowired
  private final AdvertRepo advertRepo;

  @Autowired
  private final PersonRepo personRepo;

  public CommentServiceImpl(CommentsRepo commentsRepo,
      CommentMapper commentMapper, AdvertRepo advertRepo, PersonRepo personRepo) {
    this.commentMapper = commentMapper;
    this.commentsRepo = commentsRepo;
    this.advertRepo = advertRepo;
    this.personRepo = personRepo;
  }
  @Override
  public CommentDTO updateComment(int commentId, AddCommentDTO addCommentDTO, String username) {
    Comments comments = commentsRepo.findById(commentId).orElseThrow(() -> new AdvertNotFoundException("Не найден комментарий"));
    if (comments.getPerson().getUsername().equals(username)) {
      logger.info("update my comment");
      comments = commentMapper.toEntity(addCommentDTO);
      comments.setCreateAt(LocalDateTime.now());
      commentsRepo.save(comments);
      return commentMapper.toDTO(comments);
    }
    else {
      throw new AccessDenied("Это не ваш комментарий");
    }
  }

  @Override
  public List<CommentDTO> getAllMyComments(String username) {
    logger.info("Get All my comments");
    int personId = personRepo.findByUsername(username).get().getId();
    List<Comments> commentsList = commentsRepo.findByPersonId(personId);
    if (commentsList == null) {
      return new ArrayList<>();
    }
    else {
      return commentMapper.toListDTO(commentsList);
    }
  }

  @Override
  public CommentDTO addComment(int advertId, AddCommentDTO addCommentDTO, String username) {
    logger.info("Add comment");
    Comments comments = commentMapper.toEntity(addCommentDTO);
    int personId = personRepo.findByUsername(username).get().getId();
    comments.setAdvert(advertRepo.findById(advertId).get());
    comments.setPerson(personRepo.findById(personId).get());
    comments.setCreateAt(LocalDateTime.now());
    commentsRepo.save(comments);
    return commentMapper.toDTO(comments);
  }

  @Override
  public List<CommentDTO> getAllAdvertComments(int advertId) {
    List<Comments> commentsList = commentsRepo.findByAdvertId(advertId);
    if (commentsList == null) {
      logger.warn("Don`t comments");
      return new ArrayList<>();
    }
    else {
      logger.info("All comments");
      return commentMapper.toListDTO(commentsList);
    }
  }

  @Override
  public void deleteComment(int commentId, String username) {
    Comments comment = commentsRepo.findById(commentId).orElseThrow(() -> new AdvertNotFoundException("Не найден комментарий"));
    if (comment.getPerson().getUsername().equals(username)) {
      logger.info("Delete comment");
      commentsRepo.deleteById(commentId);
    }
    else {
      throw new AccessDenied("Это не ваш комментарий");
    }
  }

}
