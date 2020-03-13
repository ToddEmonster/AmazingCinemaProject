package cinema.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import cinema.dto.CommentDto;
import cinema.dto.MovieFull;
import cinema.persistence.entity.Account;
import cinema.persistence.entity.Comment;
import cinema.persistence.entity.Movie;
import cinema.persistence.repository.CommentRepository;
import cinema.service.ICommentService;
import utils.DtoUtils;

@Service
@Transactional
public class CommentService implements ICommentService {
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	MovieService movieService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public List<CommentDto> getAllComments() {
		
		return DtoUtils.listFromEntityStream(
				commentRepository.findAll().stream(), 
				mapper, CommentDto.class);
	}
	
	@CrossOrigin
	@Override
	public CommentDto addComment(CommentDto commentDto) {
		// Le CommentDto qu'on lui POST depuis le Front, contient deux Integers idMovie et idAccount
		// je vais chercher l'éventuel movie à partir du idMovie reçu
		Optional<Movie> optAssociatedMovie = movieService.getMovieById(commentDto.getIdMovie())
								.map(me -> mapper.map(me, Movie.class));
		
		// idem pour Account par rapport à idAccount
		Optional<Account> optAssociatedAccount = accountService.getAccountByIdAccount(commentDto.getIdAccount())
								.map(me -> mapper.map(me, Account.class));
		
		if (optAssociatedMovie.isPresent() && optAssociatedAccount.isPresent()) {
			Movie associatedMovie = optAssociatedMovie.get();
			Account associatedAccount = optAssociatedAccount.get();
			
			Comment commentSaved = mapper.map(commentDto, Comment.class);
			Comment commentSavedWithEntities = mapper.map(commentDto, Comment.class);
			
			// je lui dis bien que sa propriété movie c'est le Movie qui correspond à idMovie
			commentSavedWithEntities.setMovie(associatedMovie);
			commentSavedWithEntities.setAccount(associatedAccount);
			
			// je sauvegarde dans le repo
			commentRepository.save(commentSavedWithEntities);
			
			// je remappe en Dto pour le retour
			mapper.map(commentSaved, commentDto);
			return commentDto;
		} else {
			System.out.println("We did not find the movie associated with the idMovie you posted.");
			return commentDto;
		}
	}
	
	@Override
	public Optional<CommentDto> getCommentById ( int idComment) {
		return commentRepository.findById(idComment)
				.map(me -> mapper.map(me, CommentDto.class));
		
	}
	
//	@Override
//	public Set<Comment> getCommentsByIdMovie(Integer idMovie) {
//		
//		return null;
//	}
	
	@Override
	public Set<Comment> getCommentsByMovie(Movie movie) {
		
		return null;
	}
	
	@Override
	public Set<Comment> getCommentsByIdAccount(Integer idAccount) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Optional<Comment> modifyComment(Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Comment> deleteComment(int idComment) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

}
