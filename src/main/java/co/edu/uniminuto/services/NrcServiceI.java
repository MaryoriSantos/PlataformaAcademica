package co.edu.uniminuto.services;

import java.util.List;
import co.edu.uniminuto.entity.Nrc;

public interface NrcServiceI {
    List<Nrc> findAll();
    Nrc findById(int id);
    Nrc save(Nrc nrc);
    int delete(Nrc nrc);
}