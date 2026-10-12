package co.edu.uniminuto.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import co.edu.uniminuto.entity.Grade;
import co.edu.uniminuto.jpa.JpaGrade;

@Repository
public class GradeRepository implements GradeRepositoryI {

    @Autowired
    private JpaGrade jpaGrade;

    @Override
    public Grade insertGrade(Grade grade) {
        return jpaGrade.save(grade);
    }

    @Override
    public Grade updateGrade(Grade grade) {
        return jpaGrade.save(grade);
    }

    @Override
    public int deleteGrade(Grade grade) {
        try {
            jpaGrade.delete(grade);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public Grade findByGrade(int gradeId) {
        return jpaGrade.findById(gradeId).orElse(null);
    }

    @Override
    public List<Grade> findAll() {
        return jpaGrade.findAll();
    }
}