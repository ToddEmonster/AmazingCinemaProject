package cinema.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {
	
	private Integer idComment;
	private Integer idAccount;
	private Integer idMovie;
	private Date date;
	private String comment;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comment")
	public Integer getIdComment() {
		return idComment;
	}

	public void setIdComment(Integer idComment) {
		this.idComment = idComment;
	}
	
	@ManyToOne(targetEntity = Account.class)
	@JoinColumn(nullable = false)
	public Integer getIdAccount() {
		return idAccount;
	}
	
	public void setIdAccount(Integer idAccount) {
		this.idAccount = idAccount;
	}
	
	@ManyToOne(targetEntity = Movie.class)
	@JoinColumn(nullable = false)
	public Integer getIdMovie() {
		return idMovie;
	}
	
	public void setIdMovie(Integer idMovie) {
		this.idMovie = idMovie;
	}
	
	@Column(nullable = false)
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(columnDefinition="TEXT", nullable = false)
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}

	public Comment(Integer idComment, Integer idAccount, Integer idMovie, Date date, String comment) {
		super();
		this.idComment = idComment;
		this.idAccount = idAccount;
		this.idMovie = idMovie;
		this.date = date;
		this.comment = comment;
	}

	
	
}
