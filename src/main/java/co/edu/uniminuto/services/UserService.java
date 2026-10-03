package co.edu.uniminuto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniminuto.entity.User;
import co.edu.uniminuto.repository.UserRepository;

@Service
public class UserService implements UserServiceI {

	@Autowired
	UserRepository userRepository;

	@Override
	public User insertUser(User user) {

		if (userRepository.findByEmail(user.getEmail()) == null) {
			return userRepository.insertUser(user);
		}

		return null;
	}

	@Override
	public User updateUser(User user) {

		if (userRepository.findByUser(user.getUserId()) != null) {
			return userRepository.updateUser(user);
		}

		return null;
	}

	@Override
	public int deleteUser(User user) {

	    if (userRepository.findByUser(user.getUserId()) != null) {
	        user.setStatus((byte)0);
	        userRepository.updateUser(user);
	        return 1;
	    }

	    return 0;
	}

	@Override
	public User findByUser(int userId) {
		return userRepository.findByUser(userId);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
