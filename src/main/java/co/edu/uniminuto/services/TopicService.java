package co.edu.uniminuto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniminuto.entity.Topic;
import co.edu.uniminuto.repository.TopicRepository;

@Service
public class TopicService implements TopicServiceI {

    // Permite utilizar las operaciones del repositorio
    @Autowired
    TopicRepository topicRepository;

    @Override
    public Topic insertTopic(Topic topic) {
        // Solicita al repositorio registrar el tema
        return topicRepository.insertTopic(topic);
    }

    @Override
    public Topic updateTopic(Topic topic) {
        // Comprueba que el tema exista antes de actualizarlo
        if (topicRepository.findByTopic(topic.getTopicId()) != null) {
            return topicRepository.updateTopic(topic);
        }

        // Retorna null si el tema no existe
        return null;
    }

    @Override
    public int deleteTopic(Topic topic) {
        // Comprueba que el tema exista antes de eliminarlo
        if (topicRepository.findByTopic(topic.getTopicId()) != null) {
            return topicRepository.deleteTopic(topic);
        }

        return 0;
    }

    @Override
    public Topic findByTopic(int topicId) {
        // Delega la busqueda al repositorio
        return topicRepository.findByTopic(topicId);
    }

    @Override
    public List<Topic> findAll() {
        // Delega la consulta de todos los temas al repositorio
        return topicRepository.findAll();
    }
}