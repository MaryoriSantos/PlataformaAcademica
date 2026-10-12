package co.edu.uniminuto.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
 * Representa la entidad Topic y su relacion con la tabla topics.
 */
@Entity
@Table(name = "topics")
@NamedQuery(name = "Topic.findAll", query = "SELECT t FROM Topic t")
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;

    // Identificador unico del tema
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "topic_id")
    private int topicId;

    // Descripcion del tema
    @Lob
    private String description;

    // Nombre del tema
    private String name;

    // Relacion con las recomendaciones de inteligencia artificial
    // Evita incluir esta lista en la respuesta JSON de Topic
    @JsonIgnore
    @OneToMany(mappedBy = "topic")
    private List<AiRecomendation> aiRecomendations;

    // Relacion con las preguntas asociadas al tema
    // Evita ciclos al convertir Topic a JSON
    @JsonIgnore
    @OneToMany(mappedBy = "topic")
    private List<Question> questions;

    // Relacion muchos a muchos entre temas y recursos
    // La relacion se almacena en la tabla intermedia resource_topics
    // Evita que los recursos vuelvan a incluir sus temas en la respuesta JSON
    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "resource_topics",
        joinColumns = @JoinColumn(name = "topic_id"),
        inverseJoinColumns = @JoinColumn(name = "resource_id")
    )
    private List<Resource> resources;

    // Relacion con los registros de dificultad de cada tema
    @JsonIgnore
    @OneToMany(mappedBy = "topic")
    private List<TopicDifficulty> topicDifficulties;

    // Constructor vacio utilizado por JPA
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

    public void setAiRecomendations(
            List<AiRecomendation> aiRecomendations) {
        this.aiRecomendations = aiRecomendations;
    }

    // Agrega una recomendacion y la relaciona con este tema
    public AiRecomendation addAiRecomendation(
            AiRecomendation aiRecomendation) {
        getAiRecomendations().add(aiRecomendation);
        aiRecomendation.setTopic(this);
        return aiRecomendation;
    }

    // Quita una recomendacion de este tema
    public AiRecomendation removeAiRecomendation(
            AiRecomendation aiRecomendation) {
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

    // Agrega una pregunta y la relaciona con este tema
    public Question addQuestion(Question question) {
        getQuestions().add(question);
        question.setTopic(this);
        return question;
    }

    // Quita una pregunta de este tema
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

    public void setTopicDifficulties(
            List<TopicDifficulty> topicDifficulties) {
        this.topicDifficulties = topicDifficulties;
    }

    // Agrega un registro de dificultad al tema
    public TopicDifficulty addTopicDifficulty(
            TopicDifficulty topicDifficulty) {
        getTopicDifficulties().add(topicDifficulty);
        topicDifficulty.setTopic(this);
        return topicDifficulty;
    }

    // Quita un registro de dificultad del tema
    public TopicDifficulty removeTopicDifficulty(
            TopicDifficulty topicDifficulty) {
        getTopicDifficulties().remove(topicDifficulty);
        topicDifficulty.setTopic(null);
        return topicDifficulty;
    }
}