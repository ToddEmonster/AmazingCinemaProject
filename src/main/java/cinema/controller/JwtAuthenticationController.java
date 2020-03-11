package cinema.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cinema.config.JwtTokenUtil;
import cinema.dto.AccountDto;
import cinema.model.JwtRequest;
import cinema.model.JwtResponse;
import cinema.model.JwtTokenRequest;
import cinema.service.IAccountService;
import cinema.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	IAccountService accountService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	// Changé, avant c'était direct le UserDetailsService du framework Spring
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	
	
	@RequestMapping(value="/isLogged", method=RequestMethod.POST)
	public ResponseEntity<?> loggedUserFromToken (
			@RequestBody JwtTokenRequest tokenFromFront
			) {
		String loggedUsername = jwtTokenUtil.getUserNameFromToken(tokenFromFront.getToken());
		
		// En vrai je renvoie ce que je veux si je veux !
		UserDetails firstResponse = jwtUserDetailsService.loadUserByUsername(loggedUsername);
		
		Optional<AccountDto> secondResponse = accountService.getAccountByUsername(loggedUsername);
		
//		any finalResponse = map;
		
		return ResponseEntity.ok(secondResponse);
	}
	
	// La reponse dans firstResponse ressemble à ça :
//	{
//	    "password": "$2a$10$RGW2xByBigGbX3N/7lb14O4CcoE6o023az12AuX3gM.CyVFLTxWCO",
//	    "username": "HellKore",
//	    "authorities": [],
//	    "accountNonExpired": true,
//	    "accountNonLocked": true,
//	    "credentialsNonExpired": true,
//	    "enabled": true
//	}
	
	
	
	
	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(
			// Ce que le Front m'envoie en requête, qui prend la forme du JwtRequest
			@RequestBody JwtRequest authenticationRequest
		) throws Exception { 
		// J'appelle la méthode d'authentification en prenant les identifiants fournis par le Front
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		
		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername()) ;
		
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	
	// "je prends le username et le password en entrée
	private void authenticate(String username, String password) throws Exception {
		try {
			// j'essaie de générer un Token d'authentification sur la base des identifiants fournis, ssi l'identification
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));	
		} catch(DisabledException e) {
			throw new Exception("USER_DISABLED", e); 
		} catch(BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		
	}
}
