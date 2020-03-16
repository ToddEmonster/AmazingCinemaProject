package cinema.controller;

import java.util.Optional;

import org.modelmapper.ModelMapper;
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
import cinema.model.LoggedUserResponse;
import cinema.persistence.repository.AccountRepository;
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
	AccountRepository accountRepository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	
	
	@RequestMapping(value="/whoIsLogged", method=RequestMethod.PUT)
	public ResponseEntity<?> loggedUserFromToken (
			@RequestBody JwtTokenRequest tokenFromFront
			) {
		String loggedUsername = jwtTokenUtil.getUserNameFromToken(tokenFromFront.getToken());
		
		// First version of the response
		UserDetails firstResponse = jwtUserDetailsService.loadUserByUsername(loggedUsername);
		
		return ResponseEntity.ok(
					accountService.getAccountByUsername(loggedUsername)
					.map(re -> mapper.map(re, LoggedUserResponse.class))
				);
	}
	
	@RequestMapping(value="/userInfo", method=RequestMethod.POST)
	public ResponseEntity<?> getFullUser (
			@RequestBody String username
			) {
		Optional<AccountDto> fullUser = accountService.getAccountByUsername(username);
		
		return ResponseEntity.ok(fullUser);
	}	
	
	@RequestMapping(value="/authenticate", method=RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(
			// Ce que le Front m'envoie en requête, qui prend la forme du JwtRequest
			@RequestBody JwtRequest authenticationRequest
		) throws Exception { 
		// J'appelle la méthode d'authentification en prenant les identifiants fournis par le Front
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		
		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername()) ;
		
		// TODO : aussi faire passer l'info dans le Back que le user est connecté, typiquement AccountService.login(paramètres))
		final String token = jwtTokenUtil.generateToken(userDetails);
		final String username = authenticationRequest.getUsername();
		final int idUser = accountRepository.findByUsername(username).get().getIdUser();
		final boolean isAdmin = accountRepository.findByUsername(username).get().getIsAdmin();
		
		return ResponseEntity.ok(new JwtResponse(token, username, idUser, isAdmin));
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
