package co.edu.uniminuto.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id")
	private int userId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	private String password;

	private byte status;

	//bi-directional many-to-one association to AiRecomendation
	@OneToMany(mappedBy="user")
	private List<AiRecomendation> aiRecomendations;

	//bi-directional many-to-one association to Answer
	@OneToMany(mappedBy="user")
	private List<Answer> answers;

	//bi-directional many-to-one association to Deliverable
	@OneToMany(mappedBy="user")
	private List<Deliverable> deliverables;

	//bi-directional many-to-one association to Grade
	@OneToMany(mappedBy="user")
	private List<Grade> grades;

	//bi-directional many-to-one association to Notification
	@OneToMany(mappedBy="user")
	private List<Notification> notifications;

	//bi-directional many-to-one association to NrcEnrollment
	@OneToMany(mappedBy="user")
	private List<NrcEnrollment> nrcEnrollments;

	//bi-directional many-to-one association to Nrc
	@OneToMany(mappedBy="user")
	private List<Nrc> nrcs;

	//bi-directional many-to-one association to Question
	@OneToMany(mappedBy="user")
	private List<Question> questions;

	//bi-directional many-to-one association to ResourceRating
	@OneToMany(mappedBy="user")
	private List<ResourceRating> resourceRatings;

	//bi-directional many-to-one association to Resource
	@OneToMany(mappedBy="user")
	private List<Resource> resources;

	//bi-directional many-to-one association to TopicDifficulty
	@OneToMany(mappedBy="user")
	private List<TopicDifficulty> topicDifficulties;

	//bi-directional many-to-one association to Role
	@ManyToOne
	private Role role;

	public User() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public List<AiRecomendation> getAiRecomendations() {
		return this.aiRecomendations;
	}

	public void setAiRecomendations(List<AiRecomendation> aiRecomendations) {
		this.aiRecomendations = aiRecomendations;
	}

	public AiRecomendation addAiRecomendation(AiRecomendation aiRecomendation) {
		getAiRecomendations().add(aiRecomendation);
		aiRecomendation.setUser(this);

		return aiRecomendation;
	}

	public AiRecomendation removeAiRecomendation(AiRecomendation aiRecomendation) {
		getAiRecomendations().remove(aiRecomendation);
		aiRecomendation.setUser(null);

		return aiRecomendation;
	}

	public List<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Answer addAnswer(Answer answer) {
		getAnswers().add(answer);
		answer.setUser(this);

		return answer;
	}

	public Answer removeAnswer(Answer answer) {
		getAnswers().remove(answer);
		answer.setUser(null);

		return answer;
	}

	public List<Deliverable> getDeliverables() {
		return this.deliverables;
	}

	public void setDeliverables(List<Deliverable> deliverables) {
		this.deliverables = deliverables;
	}

	public Deliverable addDeliverable(Deliverable deliverable) {
		getDeliverables().add(deliverable);
		deliverable.setUser(this);

		return deliverable;
	}

	public Deliverable removeDeliverable(Deliverable deliverable) {
		getDeliverables().remove(deliverable);
		deliverable.setUser(null);

		return deliverable;
	}

	public List<Grade> getGrades() {
		return this.grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public Grade addGrade(Grade grade) {
		getGrades().add(grade);
		grade.setUser(this);

		return grade;
	}

	public Grade removeGrade(Grade grade) {
		getGrades().remove(grade);
		grade.setUser(null);

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
		notification.setUser(this);

		return notification;
	}

	public Notification removeNotification(Notification notification) {
		getNotifications().remove(notification);
		notification.setUser(null);

		return notification;
	}

	public List<NrcEnrollment> getNrcEnrollments() {
		return this.nrcEnrollments;
	}

	public void setNrcEnrollments(List<NrcEnrollment> nrcEnrollments) {
		this.nrcEnrollments = nrcEnrollments;
	}

	public NrcEnrollment addNrcEnrollment(NrcEnrollment nrcEnrollment) {
		getNrcEnrollments().add(nrcEnrollment);
		nrcEnrollment.setUser(this);

		return nrcEnrollment;
	}

	public NrcEnrollment removeNrcEnrollment(NrcEnrollment nrcEnrollment) {
		getNrcEnrollments().remove(nrcEnrollment);
		nrcEnrollment.setUser(null);

		return nrcEnrollment;
	}

	public List<Nrc> getNrcs() {
		return this.nrcs;
	}

	public void setNrcs(List<Nrc> nrcs) {
		this.nrcs = nrcs;
	}

	public Nrc addNrc(Nrc nrc) {
		getNrcs().add(nrc);
		nrc.setUser(this);

		return nrc;
	}

	public Nrc removeNrc(Nrc nrc) {
		getNrcs().remove(nrc);
		nrc.setUser(null);

		return nrc;
	}

	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Question addQuestion(Question question) {
		getQuestions().add(question);
		question.setUser(this);

		return question;
	}

	public Question removeQuestion(Question question) {
		getQuestions().remove(question);
		question.setUser(null);

		return question;
	}

	public List<ResourceRating> getResourceRatings() {
		return this.resourceRatings;
	}

	public void setResourceRatings(List<ResourceRating> resourceRatings) {
		this.resourceRatings = resourceRatings;
	}

	public ResourceRating addResourceRating(ResourceRating resourceRating) {
		getResourceRatings().add(resourceRating);
		resourceRating.setUser(this);

		return resourceRating;
	}

	public ResourceRating removeResourceRating(ResourceRating resourceRating) {
		getResourceRatings().remove(resourceRating);
		resourceRating.setUser(null);

		return resourceRating;
	}

	public List<Resource> getResources() {
		return this.resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public Resource addResource(Resource resource) {
		getResources().add(resource);
		resource.setUser(this);

		return resource;
	}

	public Resource removeResource(Resource resource) {
		getResources().remove(resource);
		resource.setUser(null);

		return resource;
	}

	public List<TopicDifficulty> getTopicDifficulties() {
		return this.topicDifficulties;
	}

	public void setTopicDifficulties(List<TopicDifficulty> topicDifficulties) {
		this.topicDifficulties = topicDifficulties;
	}

	public TopicDifficulty addTopicDifficulty(TopicDifficulty topicDifficulty) {
		getTopicDifficulties().add(topicDifficulty);
		topicDifficulty.setUser(this);

		return topicDifficulty;
	}

	public TopicDifficulty removeTopicDifficulty(TopicDifficulty topicDifficulty) {
		getTopicDifficulties().remove(topicDifficulty);
		topicDifficulty.setUser(null);

		return topicDifficulty;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}