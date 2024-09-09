package humanclound.commentservice.app.controller;

import humanclound.commentservice.app.entity.Comment;
import humanclound.commentservice.app.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/commentservice")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;


    @PostMapping()
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment){
        Comment commentResponseEntity = commentService.saveComment(comment);
        return  ResponseEntity.ok(commentResponseEntity);

    }


    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id){
        Comment commentById = commentService.getCommentById(id);

        return  ResponseEntity.ok(commentById);

    }


    @GetMapping("/taskId/{id}")
    public ResponseEntity<List<Comment>> getComentByTaskId(@PathVariable Long id){
        List<Comment> commentByTaskId = commentService.getCommentByTaskId(id);
        return  ResponseEntity.ok(commentByTaskId);
    }


}
