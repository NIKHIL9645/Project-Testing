package humanclound.commentservice.app.service;

import humanclound.commentservice.app.entity.Comment;
import humanclound.commentservice.app.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentRepo commentRepo;


    @Override
    public Comment saveComment(Comment comment) {
        Comment save = commentRepo.save(comment);
        return save;
    }


    @Override
    public Comment getCommentById(Long id) {
        Comment commentNotFound = commentRepo.findById(id).orElseThrow(() -> new RuntimeException("Comment Not Found"));
        return commentNotFound;
    }


//    getComment by taskId

    public List<Comment> getCommentByTaskId(Long id){
        System.out.println(id);
        List<Comment> byTaskId = commentRepo.findByTaskId(id);
        System.out.println(byTaskId);
        return  byTaskId;
    }


//    getcomment By UserId


}
