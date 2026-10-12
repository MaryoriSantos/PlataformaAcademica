package co.edu.uniminuto.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniminuto.entity.Subject;
import co.edu.uniminuto.repository.SubjectRepository;

@Service
public class SubjectService implements SubjectServiceI {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject findById(int id) {
        return subjectRepository.findBySubject(id);
    }

    @Override
    public Subject save(Subject subject) {
        return subjectRepository.insertSubject(subject);
    }

    @Override
    public int delete(Subject subject) {
        return subjectRepository.deleteSubject(subject);
    }
}