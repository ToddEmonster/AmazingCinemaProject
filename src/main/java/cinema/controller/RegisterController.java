package cinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cinema.dto.AccountDto;
import cinema.service.impl.AccountService;

@CrossOrigin
@RestController
public class RegisterController {
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity <?> createRegistration(@RequestBody AccountDto newUserDto){
		
		return ResponseEntity.ok(accountService.createAccount(newUserDto));
	}
	
}
