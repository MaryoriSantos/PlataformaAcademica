package co.edu.uniminuto.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.uniminuto.entity.User;
import co.edu.uniminuto.jpa.JpaUser;

@Repository
public class UserRepository implements UserRepositoryI {

	@Autowired
	JpaUser jpaUser;

	@Override
	public User insertUser(User user) {
		// sirve tanto para catualizar como para insentar --> save ()
		return jpaUser.save(user);
	}

	@Override
	public User updateUser(User user) { 	

		return jpaUser.save(user);
	}

	@Override
	public int deleteUser(User user) {
		if (!jpaUser.save(user).equals(null))
			return 1;
		return 0;
	}

	@Override
	public User findByUser(int userId) {

		return jpaUser.findById(userId).orElse(null);
	}

	@Override
	public List<User> findAll() {

		return jpaUser.findAll();
	}

	@Override
	public User findByEmail(String email) {

		return jpaUser.findByEmail(email);
	}

	@Override
	public User login(String email, String password) {

		return null;
	}

}