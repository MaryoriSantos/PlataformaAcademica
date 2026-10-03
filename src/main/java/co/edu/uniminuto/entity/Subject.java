package co.edu.uniminuto.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the subjects database table.
 * 
 */
@Entity
@Table(name="subjects")
@NamedQuery(name="Subject.findAll", query="SELECT s FROM Subject s")
public class Subject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="subject_id")
	private int subjectId;

	private int credits;

	@Lob
	private String description;

	private String name;

	private byte status;

	//bi-directional many-to-one association to Nrc
	@OneToMany(mappedBy="subject")
	private List<Nrc> nrcs;

	public Subject() {
	}

	public int getSubjectId() {
		return this.subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getCredits() {
		return this.credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
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

	public byte getStatus() {
		return this.status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public List<Nrc> getNrcs() {
		return this.nrcs;
	}

	public void setNrcs(List<Nrc> nrcs) {
		this.nrcs = nrcs;
	}

	public Nrc addNrc(Nrc nrc) {
		getNrcs().add(nrc);
		nrc.setSubject(this);

		return nrc;
	}

	public Nrc removeNrc(Nrc nrc) {
		getNrcs().remove(nrc);
		nrc.setSubject(null);

		return nrc;
	}

}