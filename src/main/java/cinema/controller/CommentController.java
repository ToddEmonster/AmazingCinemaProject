package cinema.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cinema.dto.CommentDto;
import cinema.exception.MovieNotFoundException;
import cinema.persistence.entity.Comment;
import cinema.service.ICommentService;
import cinema.service.impl.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	
	@Autowired
	ICommentService commentService;
	
	
	@CrossOrigin
	@GetMapping
	@ResponseBody
	public List<CommentDto> allComments() {
		return commentService.getAllComments();
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

}
