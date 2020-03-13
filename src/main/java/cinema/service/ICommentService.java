package cinema.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


import cinema.dto.CommentDto;
import cinema.persistence.entity.Comment;
import cinema.persistence.entity.Movie;

public interface ICommentService {
	
	

	/*
	 * CREATE
	 */
	CommentDto addComment (CommentDto comment);
	
	/*
	 * READ
	 */
	List<CommentDto> getAllComments();
	Optional<CommentDto> getCommentById ( int idComment);
//	Set<Comment> getCommentsByIdMovie(Integer idMovie);
	Set<Comment> getCommentsByMovie(Movie movie);
	Set<Comment> getCommentsByIdAccount(Integer idAccount);
	
	/*
	 * UPDATE
	 */
	Optional<Comment> modifyComment(Comment comment);
	
	/*
	 * DELETE
	 */
	Optional<Comment> deleteComment(int idComment );

}
