package cinema.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cinema.dto.AccountDto;
import cinema.service.IAccountService;

@RestController
@RequestMapping("api/account")
public class AccountController {
	
	@Autowired
	IAccountService AccountService;
	
	@GetMapping
	@ResponseBody
	public List<AccountDto> allUsers() {
		return AccountService.getAllUsers();
	}
	
	
	// See if someone is logged in
	@GetMapping("/isLogged")
	@ResponseBody
	public Set<AccountDto> isSomeoneLoggedIn() {
		return AccountService.showIfSomeoneIsLoggedIn();
	}
	
	// Create account
	@PostMapping
	@ResponseBody
	public AccountDto createAccount(@RequestBody AccountDto account) {
		return AccountService.createAccount(account);
	}
	
	// Login account
	@PutMapping("/login")
	@ResponseBody
	public Optional<AccountDto> login(@RequestParam("em") String email,
						@RequestParam("p") String password) {
		return AccountService.loginAccount(email, password);
	}	
	
	// Logout account
	@PutMapping("/logout")
	@ResponseBody
	public String logout(@RequestParam("u") String username) {
		return AccountService.logoutAccount(username);
	}	
	
	// Set account admin
	@PutMapping("/setAdmin")
	@ResponseBody
	public Optional<AccountDto> setAccountAdmin(@RequestBody AccountDto account) {
		return AccountService.setAccountAdmin(account);
	}
	
	
	// Deprive an account from admin privileges
	@PutMapping("/setAsNotAdmin")
	@ResponseBody
	public Optional<AccountDto> setAccountNotAdmin(@RequestBody AccountDto account) {
		return AccountService.setAccountNonAdmin(account);
	}
}
