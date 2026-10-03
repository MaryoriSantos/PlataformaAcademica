package co.edu.uniminuto.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


/**
 * The persistent class for the topics database table.
 * 
 */
@Entity
@Table(name="topics")
@NamedQuery(name="Topic.findAll", query="SELECT t FROM Topic t")
public class Topic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="topic_id")
	private int topicId;

	@Lob
	private String description;

	private String name;

	//bi-directional many-to-one association to AiRecomendation
	@OneToMany(mappedBy="topic")
	private List<AiRecomendation> aiRecomendations;

	//bi-directional many-to-one association to Questiono
	@OneToMany(mappedBy="topic")
	private List<Question> questions;
	
	//bi-directional many-to-many association to Resource
	@ManyToMany
	@JoinTable(
	    name = "resource_topics",
	    joinColumns = @JoinColumn(name = "topic_id"),
	    inverseJoinColumns = @JoinColumn(name = "resource_id")
	)
	private List<Resource> resources;


	//bi-directional many-to-one association to TopicDifficulty
	@OneToMany(mappedBy="topic")
	private List<TopicDifficulty> topicDifficulties;

	public Topic() {
	}

	public int getTopicId() {
		return this.topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AiRecomendation> getAiRecomendations() {
		return this.aiRecomendations;
	}

	public void setAiRecomendations(List<AiRecomendation> aiRecomendations) {
		this.aiRecomendations = aiRecomendations;
	}

	public AiRecomendation addAiRecomendation(AiRecomendation aiRecomendation) {
		getAiRecomendations().add(aiRecomendation);
		aiRecomendation.setTopic(this);

		return aiRecomendation;
	}

	public AiRecomendation removeAiRecomendation(AiRecomendation aiRecomendation) {
		getAiRecomendations().remove(aiRecomendation);
		aiRecomendation.setTopic(null);

		return aiRecomendation;
	}

	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Question addQuestion(Question question) {
		getQuestions().add(question);
		question.setTopic(this);

		return question;
	}

	public Question removeQuestion(Question question) {
		getQuestions().remove(question);
		question.setTopic(null);

		return question;
	}

	public List<Resource> getResources() {
		return this.resources;
	}

	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	public List<TopicDifficulty> getTopicDifficulties() {
		return this.topicDifficulties;
	}

	public void setTopicDifficulties(List<TopicDifficulty> topicDifficulties) {
		this.topicDifficulties = topicDifficulties;
	}

	public TopicDifficulty addTopicDifficulty(TopicDifficulty topicDifficulty) {
		getTopicDifficulties().add(topicDifficulty);
		topicDifficulty.setTopic(this);

		return topicDifficulty;
	}

	public TopicDifficulty removeTopicDifficulty(TopicDifficulty topicDifficulty) {
		getTopicDifficulties().remove(topicDifficulty);
		topicDifficulty.setTopic(null);

		return topicDifficulty;
	}

}