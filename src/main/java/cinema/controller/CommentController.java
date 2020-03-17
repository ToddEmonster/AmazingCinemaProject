package cinema.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cinema.dto.CommentDto;
import cinema.dto.MovieFull;
import cinema.exception.MovieNotFoundException;
import cinema.persistence.entity.Comment;
import cinema.persistence.entity.Movie;
import cinema.service.ICommentService;
import cinema.service.impl.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	
	@Autowired
	ICommentService commentService;
	
	@Autowired
	ModelMapper mapper;
	
	@CrossOrigin
	@GetMapping
	@ResponseBody
	public List<CommentDto> allComments() {
		return commentService.getAllComments();
	}

	@CrossOrigin
	@GetMapping("/byMovieId")
	@ResponseBody
	public Set<CommentDto> getByIdMovie(@RequestParam("m") Integer idMovie) {
		return commentService.getCommentsByIdMovie(idMovie);
	}
	
	@CrossOrigin
	@GetMapping("/byMovie")
	@ResponseBody
	public Set<CommentDto> getByMovie(@RequestParam("m") Movie movie) {
		return commentService.getCommentsByMovie(movie);
	}
	
	@CrossOrigin
	@PostMapping
	@ResponseBody
	public CommentDto addComment(@RequestBody CommentDto comment) {
		return commentService.addComment(comment);
	}

	@CrossOrigin
	@GetMapping("/{id}")
	@ResponseBody
	public Optional<CommentDto>  commentById(@PathVariable("id") int idComment) {
		Optional<CommentDto> fullComment = commentService.getCommentById(idComment);
		if(fullComment.isPresent()) {
			return fullComment;
		}
		
		throw new MovieNotFoundException();
	}
	
	
	@CrossOrigin
	@DeleteMapping("/{id}")
	@ResponseBody
	public Optional<CommentDto> deleteComment(@PathVariable("id") int idComment) {
		return commentService.deleteComment(idComment);
	}
	
	@CrossOrigin
	@PutMapping
	@ResponseBody
	public CommentDto modifyComment (@RequestBody CommentDto comment) {
		 return commentService.modifyComment(comment);
	}
}
