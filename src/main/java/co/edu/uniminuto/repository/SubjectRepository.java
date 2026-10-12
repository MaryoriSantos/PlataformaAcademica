package co.edu.uniminuto.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import co.edu.uniminuto.entity.Subject;
import co.edu.uniminuto.jpa.JpaSubject;

@Repository
public class SubjectRepository implements SubjectRepositoryI {

    @Autowired
    private JpaSubject jpaSubject;

    @Override
    public Subject insertSubject(Subject subject) {
        return jpaSubject.save(subject);
    }

    @Override
    public Subject updateSubject(Subject subject) {
        return jpaSubject.save(subject);
    }

    @Override
    public int deleteSubject(Subject subject) {
        try {
            jpaSubject.delete(subject);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Subject findBySubject(int subjectId) {
        return jpaSubject.findById(subjectId).orElse(null);
    }

    @Override
    public List<Subject> findAll() {
        return jpaSubject.findAll();
    }
}