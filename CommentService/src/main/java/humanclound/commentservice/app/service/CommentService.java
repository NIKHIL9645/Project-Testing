package humanclound.commentservice.app.service;

import humanclound.commentservice.app.entity.Comment;
import org.springframework.http.ResponseEntity;

public interface CommentService {

    public Comment saveComment(Comment comment);

    Comment getCommentById(Long id);






}
