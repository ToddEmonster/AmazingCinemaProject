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
	Set<CommentDto> getCommentsByIdMovie(Integer idMovie);
	Set<CommentDto> getCommentsByMovie(Movie movie);
	Set<CommentDto> getCommentsByIdAccount(Integer idAccount);
//	Set<Comment> getCommentsByIdMovie(Integer idMovie);
	
	/*
	 * UPDATE
	 */
	Optional<CommentDto> modifyComment(Comment comment);
	
	/*
	 * DELETE
	 */
	Optional<CommentDto> deleteComment(int idComment );


}
