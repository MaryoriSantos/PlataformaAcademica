package co.edu.uniminuto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniminuto.entity.User;
import co.edu.uniminuto.services.UserServiceI;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	UserServiceI userService;

	@GetMapping
	public List<User> getAllUsers() {
		return userService.findAll();
	}

	@GetMapping("/id")
	public User getByUser(@RequestParam int id) {
		return userService.findByUser(id);
	}

	@GetMapping("/email")
	public User getByEmail(@RequestParam String email) {
		return userService.findByEmail(email);
	}

	@PostMapping
	public User postUser(@RequestBody User user) {
		return userService.insertUser(user);
	}
	
	@PutMapping
	public User putUser(@RequestBody User user) {
	    return userService.updateUser(user);
	}
	
}