package co.edu.uniminuto.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the nrcs database table.
 * 
 */
@Entity
@Table(name="nrcs")
@NamedQuery(name="Nrc.findAll", query="SELECT n FROM Nrc n")
public class Nrc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="nrc_id")
	private int nrcId;

	@Column(name="academic_period")
	private String academicPeriod;

	@Column(name="access_code")
	private String accessCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="nrc_code")
	private String nrcCode;

	private byte status;

	//bi-directional many-to-one association to AiRecomendation
	@OneToMany(mappedBy="nrc")
	private List<AiRecomendation> aiRecomendations;

	//bi-directional many-to-one association to Deliverable
	@OneToMany(mappedBy="nrc")
	private List<Deliverable> deliverables;

	//bi-directional many-to-one association to NrcEnrollment
	@OneToMany(mappedBy="nrc")
	private List<NrcEnrollment> nrcEnrollments;

	//bi-directional many-to-one association to Subject
	@ManyToOne
@JoinColumn(name="subject_id")
	private Subject subject;

	//bi-directional many-to-one association to User
	@ManyToOne
@JoinColumn(name="creator_id")
	private User user;

	//bi-directional many-to-one association to Question
	@OneToMany(mappedBy="nrc")
	private List<Question> questions;

	//bi-directional many-to-one association to Resource
	@OneToMany(mappedBy="nrc")
	private List<Resource> resources;

	//bi-directional many-to-one association to TopicDifficulty
	@OneToMany(mappedBy="nrc")
	private List<TopicDifficulty> topicDifficulties;

	public Nrc() {
	}

	public int getNrcId() {
		return this.nrcId;
	}

	public void setNrcId(int nrcId) {
		this.nrcId = nrcId;
	}

	public String getAcademicPeriod() {
		return this.academicPeriod;
	}

	public void setAcademicPeriod(String academicPeriod) {
		this.academicPeriod = academicPeriod;
	}

	public String getAccessCode() {
		return this.accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getNrcCode() {
		return this.nrcCode;
	}

	public void setNrcCode(String nrcCode) {
		this.nrcCode = nrcCode;
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
		aiRecomendation.setNrc(this);

		return aiRecomendation;
	}

	public AiRecomendation removeAiRecomendation(AiRecomendation aiRecomendation) {
		getAiRecomendations().remove(aiRecomendation);
		aiRecomendation.setNrc(null);

		return aiRecomendation;
	}

	public List<Deliverable> getDeliverables() {
		return this.deliverables;
	}

	public void setDeliverables(List<Deliverable> deliverables) {
		this.deliverables = deliverables;
	}

	public Deliverable addDeliverable(Deliverable deliverable) {
		getDeliverables().add(deliverable);
		deliverable.setNrc(this);

		return deliverable;
	}

	public Deliverable removeDeliverable(Deliverable deliverable) {
		getDeliverables().remove(deliverable);
		deliverable.setNrc(null);

		return deliverable;
	}

	public List<NrcEnrollment> getNrcEnrollments() {
		return this.nrcEnrollments;
	}

	public void setNrcEnrollments(List<NrcEnrollment> nrcEnrollments) {
		this.nrcEnrollments = nrcEnrollments;
	}

	public NrcEnrollment addNrcEnrollment(NrcEnrollment nrcEnrollment) {
		getNrcEnrollments().add(nrcEnrollment);
		nrcEnrollment.setNrc(this);

		return nrcEnrollment;
	}

	public NrcEnrollment removeNrcEnrollment(NrcEnrollment nrcEnrollment) {
		getNrcEnrollments().remove(nrcEnrollment);
		nrcEnrollment.setNrc(null);

		return nrcEnrollment;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Question addQuestion(Question question) {
		getQuestions().add(question);
		question.setNrc(this);

		return question;
	}

	public Question removeQuestion(Question question) {
		getQuestions().remove(question);
		question.setNrc(null);

		return question;
	}

	public List<Resource> getResources() {
		return this.resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public Resource addResource(Resource resource) {
		getResources().add(resource);
		resource.setNrc(this);

		return resource;
	}

	public Resource removeResource(Resource resource) {
		getResources().remove(resource);
		resource.setNrc(null);

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
		topicDifficulty.setNrc(this);

		return topicDifficulty;
	}

	public TopicDifficulty removeTopicDifficulty(TopicDifficulty topicDifficulty) {
		getTopicDifficulties().remove(topicDifficulty);
		topicDifficulty.setNrc(null);

		return topicDifficulty;
	}

}