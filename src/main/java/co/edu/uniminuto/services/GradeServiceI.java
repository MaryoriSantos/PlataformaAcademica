package co.edu.uniminuto.services;

import java.util.List;
import co.edu.uniminuto.entity.Grade;

public interface GradeServiceI {
    Grade insertGrade(Grade grade);
    Grade updateGrade(Grade grade);
    int deleteGrade(Grade grade);
    Grade findByGrade(int gradeId);
    List<Grade> findAll();
}