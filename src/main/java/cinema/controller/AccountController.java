package cinema.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cinema.dto.AccountDto;
import cinema.service.IAccountService;
import cinema.service.IMovieService;

@RestController
@RequestMapping("api/account")
public class AccountController {
	
	@Autowired
	IAccountService AccountService;

	@Autowired
	IMovieService MovieService;
	
	@CrossOrigin
	@GetMapping
	@ResponseBody
	public List<AccountDto> allUsers() {
		return AccountService.getAllUsers();
	}
	
	@CrossOrigin
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseEntity<?> getUserById(
			@PathVariable("id") Integer idUser
			) {
		return ResponseEntity.ok(AccountService.getAccountByIdUser(idUser));
	}
	
	
	// See if someone is logged in
	@CrossOrigin
	@GetMapping("/isLogged")
	@ResponseBody
	public Set<AccountDto> isSomeoneLoggedIn() {
		return AccountService.showIfSomeoneIsLoggedIn();
	}
	
	 
	// Create account
	@CrossOrigin
	@PostMapping("/register")
	@ResponseBody
	public AccountDto createAccount(@RequestBody AccountDto account) {
		return AccountService.createAccount(account);
	}
	
	// Login account
	@CrossOrigin
	@PutMapping("/login")
	@ResponseBody
	public Optional<AccountDto> login(@RequestParam("em") String email,
						@RequestParam("p") String password) {
		return AccountService.loginAccount(email, password);
	}	
	
	// Logout account
	@CrossOrigin
	@PutMapping("/logout")
	@ResponseBody
	public String logout(@RequestParam("u") String username) {
		return AccountService.logoutAccount(username);
	}	
	
	
	// Set account admin
	@CrossOrigin
	@PutMapping("/setAsAdmin")
	@ResponseBody
	public Optional<AccountDto> setAccountAdmin(@RequestParam("u") String username) {
		return AccountService.setAccountAdmin(username);
	}
	

	// Deprive an account from admin privileges
	@CrossOrigin
	@PutMapping("/setAsNotAdmin")
	@ResponseBody
	public Optional<AccountDto> setAccountNotAdmin(@RequestParam("u") String username) {
		return AccountService.setAccountNonAdmin(username);
	}
	
//	@CrossOrigin
//	@GetMapping("/likedMovies")
//	@ResponseBody
//	public Optional<List<Integer>> getLikedMovies() {
//		return AccountService.getLikedMovies();
//	}
	
//	@CrossOrigin
//	@PostMapping("/likeMovie")
//	@ResponseBody
//	public Optional<MovieLight> likeMovie(@RequestParam("m") Integer idMovie) {
//		return AccountService.likeMovie(idMovie);
//	}
	
}
