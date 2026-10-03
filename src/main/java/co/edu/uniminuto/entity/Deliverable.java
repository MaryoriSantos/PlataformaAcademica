package co.edu.uniminuto.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the deliverables database table.
 * 
 */
@Entity
@Table(name="deliverables")
@NamedQuery(name="Deliverable.findAll", query="SELECT d FROM Deliverable d")
public class Deliverable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="deliverable_id")
	private int deliverableId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Lob
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="due_date")
	private Date dueDate;

	private String name;

	private BigDecimal percentage;

	private byte status;

	private String type;

	//bi-directional many-to-one association to Nrc
	@ManyToOne
@JoinColumn(name="nrc_id")
	private Nrc nrc;

	//bi-directional many-to-one association to User
	@ManyToOne
@JoinColumn(name="creator_id")
	private User user;

	//bi-directional many-to-one association to Grade
	@OneToMany(mappedBy="deliverable")
	private List<Grade> grades;

	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="deliverable")
	private List<Notification> notifications;

	public Deliverable() {
	}

	public int getDeliverableId() {
		return this.deliverableId;
	}

	public void setDeliverableId(int deliverableId) {
		this.deliverableId = deliverableId;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPercentage() {
		return this.percentage;
	}

	public void setPercentage(BigDecimal percentage) {
		this.percentage = percentage;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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

	public List<Grade> getGrades() {
		return this.grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public Grade addGrade(Grade grade) {
		getGrades().add(grade);
		grade.setDeliverable(this);

		return grade;
	}

	public Grade removeGrade(Grade grade) {
		getGrades().remove(grade);
		grade.setDeliverable(null);

		return grade;
	}

	public List<Notification> getNotifications() {
		return this.notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public Notification addNotification(Notification notification) {
		getNotifications().add(notification);
		notification.setDeliverable(this);

		return notification;
	}

	public Notification removeNotification(Notification notification) {
		getNotifications().remove(notification);
		notification.setDeliverable(null);

		return notification;
	}

}