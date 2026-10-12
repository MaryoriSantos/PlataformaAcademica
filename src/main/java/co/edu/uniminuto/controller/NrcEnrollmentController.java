package co.edu.uniminuto.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.uniminuto.entity.NrcEnrollment;
import co.edu.uniminuto.services.NrcEnrollmentService;

@RestController
@RequestMapping("/nrc-enrollments")
public class NrcEnrollmentController {

    @Autowired
    private NrcEnrollmentService nrcEnrollmentService;

    @GetMapping
    public List<NrcEnrollment> getAllNrcEnrollments() {
        return nrcEnrollmentService.findAll();
    }

    @GetMapping("/{id}")
    public NrcEnrollment getNrcEnrollmentById(@PathVariable int id) {
        return nrcEnrollmentService.findById(id);
    }

    @PostMapping
    public NrcEnrollment createNrcEnrollment(@RequestBody NrcEnrollment nrcEnrollment) {
        return nrcEnrollmentService.save(nrcEnrollment);
    }

    @PutMapping
    public NrcEnrollment updateNrcEnrollment(@RequestBody NrcEnrollment nrcEnrollment) {
        return nrcEnrollmentService.save(nrcEnrollment);
    }

    @DeleteMapping
    public int deleteNrcEnrollment(@RequestBody NrcEnrollment nrcEnrollment) {
        return nrcEnrollmentService.delete(nrcEnrollment);
    }
}