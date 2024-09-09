package humanclound.commentservice.app.repository;

import humanclound.commentservice.app.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment ,Long>{


    public List<Comment> findByTaskId(Long id);


}
