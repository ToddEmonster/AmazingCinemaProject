package cinema.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
			
			Comment commentSavedWithEntities = mapper.map(commentDto, Comment.class);
			
			// je lui dis bien que sa propriété movie c'est le Movie qui correspond à idMovie
			commentSavedWithEntities.setMovie(associatedMovie);
			commentSavedWithEntities.setAccount(associatedAccount);
			
			// je sauvegarde dans le repo
			commentRepository.save(commentSavedWithEntities);
			
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
	
	
	@Override
	public Set<CommentDto> getCommentsByMovie(Movie movie) {
		return commentRepository.findByMovie(movie)
				.stream()
				.map(me -> mapper.map(me, CommentDto.class))
				.collect(Collectors.toSet());
	}
	
	@Override
	public Set<CommentDto> getCommentsByAccount(Account account) {
		return commentRepository.findByAccount(account)
				.stream()
				.map(me -> mapper.map(me, CommentDto.class))
				.collect(Collectors.toSet());
	}
	
	@Override
	public Set<CommentDto> getCommentsByIdMovie(Integer idMovie) {
		Optional<Movie> optAssociatedMovie = movieService.getMovieById(idMovie)
				.map(me -> mapper.map(me, Movie.class));
		
		if (optAssociatedMovie.isPresent()) {
			Movie associatedMovie = optAssociatedMovie.get();
			
			return commentRepository.findByMovie(associatedMovie)
						.stream()
						.map(me -> mapper.map(me, CommentDto.class))
						.collect(Collectors.toSet());
		} else {
			return null;
		}
	}
	
	@Override
	public Set<CommentDto> getCommentsByIdAccount(Integer idAccount) {
		Optional<Account> optAssociatedAccount = accountService.getAccountByIdAccount(idAccount)
				.map(me -> mapper.map(me, Account.class));
		
		if (optAssociatedAccount.isPresent()) {
			Account associatedAccout = optAssociatedAccount.get();
			
			return commentRepository.findByAccount(associatedAccout)
						.stream()
						.map(me -> mapper.map(me, CommentDto.class))
						.collect(Collectors.toSet());
		} else {
			return null;
		}
	}
	
	
	
	@Override
	public CommentDto modifyComment(CommentDto comment) {
		Comment commentFromRepo = commentRepository.findByIdComment(comment.getIdComment()).get();
		commentFromRepo.setDate(comment.getDate());
		commentFromRepo.setComment(comment.getComment());
		commentRepository.flush();
		
		return mapper.map(commentFromRepo, CommentDto.class);
	}

	@Override
	public Optional<CommentDto> deleteComment(int idComment) {
		
			commentRepository.findById(idComment)
				.ifPresent(m -> {
					commentRepository.delete(m);
					commentRepository.flush();
				});
				return null;
	}
	

	

	

	

}
