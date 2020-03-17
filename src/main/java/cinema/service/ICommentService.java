package cinema.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;


import cinema.dto.CommentDto;
import cinema.persistence.entity.Account;
import cinema.persistence.entity.Comment;
import cinema.persistence.entity.Movie;

public interface ICommentService {
	
	

	/*
	 * CREATE
	 */
	CommentDto addComment (CommentDto comment);						//OK
	
	/*
	 * READ
	 */
	List<CommentDto> getAllComments();								// OK
	Optional<CommentDto> getCommentById ( int idComment);			// OK
	Set<CommentDto> getCommentsByIdMovie(Integer idMovie);			// ok
	Set<CommentDto> getCommentsByMovie(Movie movie);				 
	Set<CommentDto> getCommentsByIdAccount(Integer idAccount);
	Set<CommentDto> getCommentsByAccount(Account account);
	
//	Set<Comment> getCommentsByIdMovie(Integer idMovie);
	
	/*
	 * UPDATE
	 */
	CommentDto modifyComment(CommentDto comment);
	
	/*
	 * DELETE
	 */
	Optional<CommentDto> deleteComment(int idComment );


}
