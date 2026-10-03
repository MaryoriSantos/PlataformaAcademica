package co.edu.uniminuto.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ai_recomendations database table.
 * 
 */
@Entity
@Table(name="ai_recomendations")
@NamedQuery(name="AiRecomendation.findAll", query="SELECT a FROM AiRecomendation a")
public class AiRecomendation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="recommendation_id")
	private int recommendationId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Lob
	private String recommendation;

	@Column(name="recommendation_type")
	private String recommendationType;

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

	public AiRecomendation() {
	}

	public int getRecommendationId() {
		return this.recommendationId;
	}

	public void setRecommendationId(int recommendationId) {
		this.recommendationId = recommendationId;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getRecommendation() {
		return this.recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}

	public String getRecommendationType() {
		return this.recommendationType;
	}

	public void setRecommendationType(String recommendationType) {
		this.recommendationType = recommendationType;
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