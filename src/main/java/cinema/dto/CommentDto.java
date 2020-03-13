package cinema.dto;

import java.util.Date;



public class CommentDto {
	private Integer idComment;
	private Integer idUser;
	private Integer idMovie;
	private Date date;
	private String comment;
	
	public Integer getIdComment() {
		return idComment;
	}

	public void setIdComment(Integer idComment) {
		this.idComment = idComment;
	}
	
	
	public Integer getIdUser() {
		return idUser;
	}
	
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
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
