package cinema.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
//	private Integer idAccount;
//	private Integer idMovie;
	private Account account;
	private Movie movie;
	private Date date;
	private String comment;
	
	
	public Comment() { }
	
	public Comment(Account account, Movie movie, Date date, String comment) {
		this.account = account;
		this.movie = movie;
//		this.idMovie = idMovie;
		this.date = date;
		this.comment = comment;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_comment")
	public Integer getIdComment() {
		return idComment;
	}

	public void setIdComment(Integer idComment) {
		this.idComment = idComment;
	}
	
//	@ManyToOne(targetEntity = Account.class)
//	@JoinColumn(name = "id_account",nullable = false)
//	public Integer getIdAccount() {
//		return idAccount;
//	}
//	
//	public void setIdAccount(Integer idAccount) {
//		this.idAccount = idAccount;
//	}

//	@ManyToOne
//	@JoinColumn(name = "id_movie", nullable = false)
//	public Integer getIdMovie() {
//		return idMovie;
//	}
//	
//	public void setIdMovie(Integer idMovie) {
//		this.idMovie = idMovie;
//	}
	
	
	@ManyToOne
	@JoinColumn(name = "id_account", nullable = false)
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "id_movie", nullable = false)
	public Movie getMovie() {
		return movie;
	}


	public void setMovie(Movie movie) {
		this.movie = movie;
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



	
	
}
