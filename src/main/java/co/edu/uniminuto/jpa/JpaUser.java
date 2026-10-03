package co.edu.uniminuto.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.uniminuto.entity.User;

public interface JpaUser extends JpaRepository<User, Integer> {
	User findByEmail(String email);

}
