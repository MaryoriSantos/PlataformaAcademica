package co.edu.uniminuto.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uniminuto.entity.Role;

public interface JpaRole extends JpaRepository<Role, Integer> {

}