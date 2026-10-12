package co.edu.uniminuto.services;

import java.util.List;
import co.edu.uniminuto.entity.Grade;

public interface GradeServiceI {
    List<Grade> findAll();
    Grade findById(int id);
    Grade save(Grade grade);
    int delete(Grade grade);
}