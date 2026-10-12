package co.edu.uniminuto.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniminuto.entity.NrcEnrollment;
import co.edu.uniminuto.repository.NrcEnrollmentRepository;

@Service
public class NrcEnrollmentService implements NrcEnrollmentServiceI {

    @Autowired
    private NrcEnrollmentRepository nrcEnrollmentRepository;

    @Override
    public List<NrcEnrollment> findAll() {
        return nrcEnrollmentRepository.findAll();
    }

    @Override
    public NrcEnrollment findById(int id) {
        return nrcEnrollmentRepository.findByNrcEnrollment(id);
    }

    @Override
    public NrcEnrollment save(NrcEnrollment nrcEnrollment) {
        return nrcEnrollmentRepository.insertNrcEnrollment(nrcEnrollment);
    }

    @Override
    public int delete(NrcEnrollment nrcEnrollment) {
        return nrcEnrollmentRepository.deleteNrcEnrollment(nrcEnrollment);
    }
}