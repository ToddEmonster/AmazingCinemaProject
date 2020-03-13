package cinema.dto;

import java.util.Date;

import cinema.persistence.entity.Movie;



public class CommentDto {
	private Integer idComment;
	private Integer idAccount;
	private Integer idMovie;
	private Date date;
	private String comment;
	
	public CommentDto() {
		super();
	}

	
	public CommentDto(Date date, String comment) {
		super();
		this.date = date;
		this.comment = comment;
	}

	public Integer getIdComment() {
		return idComment;
	}

	public void setIdComment(Integer idComment) {
		this.idComment = idComment;
	}
	
	
	public Integer getIdAccount() {
		return idAccount;
	}
	
	public void setIdAccount(Integer idAccount) {
		this.idAccount = idAccount;
	}
	


	public Integer getIdMovie() {
		return idMovie;
	}
	
	public void setIdMovie(Integer idMovie) {
		this.idMovie = idMovie;
	}
	
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}

}
