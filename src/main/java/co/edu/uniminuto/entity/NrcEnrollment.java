package co.edu.uniminuto.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the nrc_enrollments database table.
 * 
 */
@Entity
@Table(name="nrc_enrollments")
@NamedQuery(name="NrcEnrollment.findAll", query="SELECT n FROM NrcEnrollment n")
public class NrcEnrollment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="enrollment_id")
	private int enrollmentId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="joined_at")
	private Date joinedAt;

	private byte status;

	//bi-directional many-to-one association to Nrc
	@ManyToOne
@JoinColumn(name="nrc_id")
	private Nrc nrc;

	//bi-directional many-to-one association to User
	@ManyToOne
@JoinColumn(name="user_id")
	private User user;

	public NrcEnrollment() {
	}

	public int getEnrollmentId() {
		return this.enrollmentId;
	}

	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public Date getJoinedAt() {
		return this.joinedAt;
	}

	public void setJoinedAt(Date joinedAt) {
		this.joinedAt = joinedAt;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Nrc getNrc() {
		return this.nrc;
	}

	public void setNrc(Nrc nrc) {
		this.nrc = nrc;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}