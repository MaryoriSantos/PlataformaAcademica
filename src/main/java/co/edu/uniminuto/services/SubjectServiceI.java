package co.edu.uniminuto.services;

import java.util.List;
import co.edu.uniminuto.entity.Subject;

public interface SubjectServiceI {
    List<Subject> findAll();
    Subject findById(int id);
    Subject save(Subject subject);
    int delete(Subject subject);
}