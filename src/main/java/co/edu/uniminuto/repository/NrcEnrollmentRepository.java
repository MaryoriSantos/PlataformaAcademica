package co.edu.uniminuto.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import co.edu.uniminuto.entity.NrcEnrollment;
import co.edu.uniminuto.jpa.JpaNrcEnrollment;

@Repository
public class NrcEnrollmentRepository implements NrcEnrollmentRepositoryI {

    @Autowired
    private JpaNrcEnrollment jpaNrcEnrollment;

    @Override
    public NrcEnrollment insertNrcEnrollment(NrcEnrollment nrcEnrollment) {
        return jpaNrcEnrollment.save(nrcEnrollment);
    }

    @Override
    public NrcEnrollment updateNrcEnrollment(NrcEnrollment nrcEnrollment) {
        return jpaNrcEnrollment.save(nrcEnrollment);
    }

    @Override
    public int deleteNrcEnrollment(NrcEnrollment nrcEnrollment) {
        try {
            jpaNrcEnrollment.delete(nrcEnrollment);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public NrcEnrollment findByNrcEnrollment(int nrcEnrollmentId) {
        return jpaNrcEnrollment.findById(nrcEnrollmentId).orElse(null);
    }

    @Override
    public List<NrcEnrollment> findAll() {
        return jpaNrcEnrollment.findAll();
    }
}