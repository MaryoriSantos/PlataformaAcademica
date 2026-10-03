package co.edu.uniminuto.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the topic_difficulties database table.
 * 
 */
@Entity
@Table(name="topic_difficulties")
@NamedQuery(name="TopicDifficulty.findAll", query="SELECT t FROM TopicDifficulty t")
public class TopicDifficulty implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="topic_difficulty_id")
	private int topicDifficultyId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Column(name="difficulty_level")
	private int difficultyLevel;

	//bi-directional many-to-one association to Nrc
	@ManyToOne
@JoinColumn(name="nrc_id")
	private Nrc nrc;

	//bi-directional many-to-one association to Topic
	@ManyToOne
@JoinColumn(name="topic_id")
	private Topic topic;

	//bi-directional many-to-one association to User
	@ManyToOne
@JoinColumn(name="user_id")
	private User user;

	public TopicDifficulty() {
	}

	public int getTopicDifficultyId() {
		return this.topicDifficultyId;
	}

	public void setTopicDifficultyId(int topicDifficultyId) {
		this.topicDifficultyId = topicDifficultyId;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getDifficultyLevel() {
		return this.difficultyLevel;
	}

	public void setDifficultyLevel(int difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public Nrc getNrc() {
		return this.nrc;
	}

	public void setNrc(Nrc nrc) {
		this.nrc = nrc;
	}

	public Topic getTopic() {
		return this.topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}