package co.edu.uniminuto.repository;

import java.util.List;
import co.edu.uniminuto.entity.Subject;

public interface SubjectRepositoryI {
    Subject insertSubject(Subject subject);
    Subject updateSubject(Subject subject);
    int deleteSubject(Subject subject);
    Subject findBySubject(int subjectId);
    List<Subject> findAll();
    Subject findByName(String name);
}