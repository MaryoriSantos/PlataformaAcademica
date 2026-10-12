package co.edu.uniminuto.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniminuto.entity.Grade;
import co.edu.uniminuto.repository.GradeRepository;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }

    public Grade findById(int id) {
        return gradeRepository.findByGrade(id);
    }

    public Grade save(Grade grade) {
        return gradeRepository.insertGrade(grade);
    }

    public int delete(Grade grade) {
        return gradeRepository.deleteGrade(grade);
    }
}