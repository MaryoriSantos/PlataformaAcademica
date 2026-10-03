package co.edu.uniminuto.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the grades database table.
 * 
 */
@Entity
@Table(name="grades")
@NamedQuery(name="Grade.findAll", query="SELECT g FROM Grade g")
public class Grade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="grade_id")
	private int gradeId;

	@Lob
	private String comments;

	private BigDecimal grade;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="graded_at")
	private Date gradedAt;

	//bi-directional many-to-one association to Deliverable
	@ManyToOne
@JoinColumn(name="deliverable_id")
	private Deliverable deliverable;

	//bi-directional many-to-one association to User
	@ManyToOne
@JoinColumn(name="user_id")
	private User user;

	public Grade() {
	}

	public int getGradeId() {
		return this.gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public BigDecimal getGrade() {
		return this.grade;
	}

	public void setGrade(BigDecimal grade) {
		this.grade = grade;
	}

	public Date getGradedAt() {
		return this.gradedAt;
	}

	public void setGradedAt(Date gradedAt) {
		this.gradedAt = gradedAt;
	}

	public Deliverable getDeliverable() {
		return this.deliverable;
	}

	public void setDeliverable(Deliverable deliverable) {
		this.deliverable = deliverable;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}