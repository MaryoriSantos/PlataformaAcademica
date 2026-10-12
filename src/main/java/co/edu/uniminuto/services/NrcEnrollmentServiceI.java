package co.edu.uniminuto.services;

import java.util.List;
import co.edu.uniminuto.entity.NrcEnrollment;

public interface NrcEnrollmentServiceI {
    NrcEnrollment insertNrcEnrollment(NrcEnrollment nrcEnrollment);
    NrcEnrollment updateNrcEnrollment(NrcEnrollment nrcEnrollment);
    int deleteNrcEnrollment(NrcEnrollment nrcEnrollment);
    NrcEnrollment findByNrcEnrollment(int nrcEnrollmentId);
    List<NrcEnrollment> findAll();
}