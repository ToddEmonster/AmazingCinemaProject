package cinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cinema.dto.AccountDto;
import cinema.service.IAccountService;

@RestController
@CrossOrigin
public class RegisterController {
	
	@Autowired
	IAccountService accountService;
	
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<?> createRegistration(
			@RequestBody AccountDto newUserDto
		) {
		
		// Lignes à rajouter si jamais le newUser qui nous vient du Front 
		// ne suit pas la forme du newUserDto attendu
		
		return ResponseEntity.ok(accountService.createAccount(newUserDto));
	}
}
