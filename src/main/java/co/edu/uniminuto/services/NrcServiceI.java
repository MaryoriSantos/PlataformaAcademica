package co.edu.uniminuto.services;

import java.util.List;
import co.edu.uniminuto.entity.Nrc;

public interface NrcServiceI {
    Nrc insertNrc(Nrc nrc);
    Nrc updateNrc(Nrc nrc);
    int deleteNrc(Nrc nrc);
    Nrc findByNrc(int nrcId);
    List<Nrc> findAll();
}