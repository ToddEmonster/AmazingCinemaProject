package cinema.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cinema.dto.CommentDto;
import cinema.dto.MovieLight;
import cinema.persistence.entity.Comment;
import cinema.persistence.repository.CommentRepository;
import cinema.service.ICommentService;
import utils.DtoUtils;

@Service
@Transactional
public class CommentService implements ICommentService {
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public List<CommentDto> getAllComments() {
		
		return DtoUtils.listFromEntityStream(
				commentRepository.findAll().stream(), 
				mapper, CommentDto.class);
	}
	
	@Override
	public CommentDto addComment(CommentDto commentDto) {
		Comment commentSaved = mapper.map(commentDto, Comment.class);
		commentRepository.save(commentSaved);
		mapper.map(commentSaved, commentDto);
		return commentDto;
	
	}
	
	@Override
	public Optional<CommentDto> getCommentById ( int idComment) {
		return commentRepository.findById(idComment)
				.map(me -> mapper.map(me, CommentDto.class));
		
	}
	
	@Override
	public Set<Comment> getCommentsByIdMovie(Integer idMovie) {
		
		return null;
	}

	@Override
	public Set<Comment> getCommentsByIdAccount(Integer idMovie) {
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
