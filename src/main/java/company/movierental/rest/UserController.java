package company.movierental.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import company.movierental.database.handlers.UserHandler;
import company.movierental.database.model.User;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserHandler userHandler;

	public UserController(UserHandler userHandler) {
		this.userHandler = userHandler;
	}
	
	@ResponseBody
	@GetMapping
	public ResponseEntity<List<User>> getMovies() {
		List<User> users = userHandler.getUsers();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	
	@PostMapping
	public ResponseEntity<Object> addUser(@RequestBody User user) {
	    if (userHandler.addUser(user)) {
	        return ResponseEntity.status(HttpStatus.CREATED).build();
	    }
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}

}
