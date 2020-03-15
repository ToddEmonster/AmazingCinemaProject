package cinema.persistence.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.persistence.entity.Movie;
import cinema.persistence.entity.Account;
import cinema.persistence.entity.Comment;

public interface CommentRepository  extends JpaRepository <Comment, Integer>{
	
	
	Optional<Comment> findById (Integer id);
	Optional<Comment> findByCommentContainingIgnoreCase(String truc);
	Set<Comment> findByMovie (Movie movie);
//	Optional<Comment> findByIdAccount (Integer idAccount);
	Optional<Comment> findByAccount (Account account);

}
