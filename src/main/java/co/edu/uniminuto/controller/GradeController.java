package co.edu.uniminuto.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.uniminuto.entity.Grade;
import co.edu.uniminuto.services.GradeService;

@RestController
@RequestMapping("/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping
    public List<Grade> getAllGrades() {
        return gradeService.findAll();
    }

    @GetMapping("/{id}")
    public Grade getGradeById(@PathVariable int id) {
        return gradeService.findById(id);
    }

    @PostMapping
    public Grade createGrade(@RequestBody Grade grade) {
        return gradeService.save(grade);
    }

    @PutMapping
    public Grade updateGrade(@RequestBody Grade grade) {
        return gradeService.save(grade);
    }

    @DeleteMapping
    public int deleteGrade(@RequestBody Grade grade) {
        return gradeService.delete(grade);
    }
}