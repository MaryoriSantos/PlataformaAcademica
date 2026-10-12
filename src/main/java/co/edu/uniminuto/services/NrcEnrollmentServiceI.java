package co.edu.uniminuto.services;

import java.util.List;
import co.edu.uniminuto.entity.NrcEnrollment;

public interface NrcEnrollmentServiceI {
    List<NrcEnrollment> findAll();
    NrcEnrollment findById(int id);
    NrcEnrollment save(NrcEnrollment nrcEnrollment);
    int delete(NrcEnrollment nrcEnrollment);
}