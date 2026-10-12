package co.edu.uniminuto.repository;

import java.util.List;
import co.edu.uniminuto.entity.Grade;

public interface GradeRepositoryI {
    Grade insertGrade(Grade grade);
    Grade updateGrade(Grade grade);
    int deleteGrade(Grade grade);
    Grade findByGrade(int gradeId);
    List<Grade> findAll();
}