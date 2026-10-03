package co.edu.uniminuto.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the resources database table.
 * 
 */
@Entity
@Table(name="resources")
@NamedQuery(name="Resource.findAll", query="SELECT r FROM Resource r")
public class Resource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="resource_id")
	private int resourceId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_at")
	private Date createdAt;

	@Lob
	private String description;

	@Column(name="resource_type")
	private String resourceType;

	@Column(name="resource_url")
	private String resourceUrl;

	private byte status;

	private String title;

	//bi-directional many-to-one association to ResourceRating
	@OneToMany(mappedBy="resource")
	private List<ResourceRating> resourceRatings;

	//bi-directional many-to-many association to Topic
	@ManyToMany(mappedBy="resources")
	private List<Topic> topics;

	//bi-directional many-to-one association to Nrc
	@ManyToOne
@JoinColumn(name="nrc_id")
	private Nrc nrc;

	//bi-directional many-to-one association to User
	@ManyToOne
@JoinColumn(name="user_id")
	private User user;

	public Resource() {
	}

	public int getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
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

	public String getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceUrl() {
		return this.resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ResourceRating> getResourceRatings() {
		return this.resourceRatings;
	}

	public void setResourceRatings(List<ResourceRating> resourceRatings) {
		this.resourceRatings = resourceRatings;
	}

	public ResourceRating addResourceRating(ResourceRating resourceRating) {
		getResourceRatings().add(resourceRating);
		resourceRating.setResource(this);

		return resourceRating;
	}

	public ResourceRating removeResourceRating(ResourceRating resourceRating) {
		getResourceRatings().remove(resourceRating);
		resourceRating.setResource(null);

		return resourceRating;
	}

	public List<Topic> getTopics() {
		return this.topics;
	}

	public void setTopics(List<Topic> topics) {
		this.topics = topics;
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