package com.example.Senla.Controller;

import java.util.List;

import com.example.Senla.DTO.AddCommentDTO;
import com.example.Senla.DTO.CommentDTO;
import com.example.Senla.Service.CommentService;
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
@RequestMapping("/comment")
@AllArgsConstructor
@Tag(name = "To work with comment")
public class CommentsController {

  @Autowired
  private final CommentService commentService;

  @PostMapping("/{advert_id}/add")
  @Operation(summary = "Create comment")
  public ResponseEntity<CommentDTO> addComment(@PathVariable("advert_id") int advert_id,
      @RequestBody AddCommentDTO addCommentDTO) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ResponseEntity.ok(
        commentService.addComment(advert_id, addCommentDTO, authentication.getName()));
  }

  @PatchMapping("/{comment_id}/update")
  @Operation(summary = "Update your comment")
  public ResponseEntity<CommentDTO> updateComment(@PathVariable int comment_id, @RequestBody AddCommentDTO addCommentDTO) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ResponseEntity.ok(commentService.updateComment(comment_id, addCommentDTO, authentication.getName()));
  }

  @GetMapping("/{advert_id}")
  @Operation(summary = "Get all comments from the ad")
  public ResponseEntity<List<CommentDTO>> getAllComments(@PathVariable int advert_id) {
    return ResponseEntity.ok(commentService.getAllAdvertComments(advert_id));
  }

  @DeleteMapping("/delete/{comment_id}")
  @Operation(summary = "Delete your comment")
  public ResponseEntity<?> deleteComment(@PathVariable int comment_id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    commentService.deleteComment(comment_id, authentication.getName());
    return ResponseEntity.ok("Комментарий удалён");
  }

  @GetMapping("/my_comments")
  @Operation(summary = "Get all your comments")
  public ResponseEntity<List<CommentDTO>> getMyComments() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return ResponseEntity.ok(commentService.getAllMyComments(authentication.getName()));
  }
}
