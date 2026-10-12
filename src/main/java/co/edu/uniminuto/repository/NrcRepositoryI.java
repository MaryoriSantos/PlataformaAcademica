package co.edu.uniminuto.repository;

import java.util.List;
import co.edu.uniminuto.entity.Nrc;

public interface NrcRepositoryI {
    Nrc insertNrc(Nrc nrc);
    Nrc updateNrc(Nrc nrc);
    int deleteNrc(Nrc nrc);
    Nrc findByNrc(int nrcId);
    List<Nrc> findAll();
}