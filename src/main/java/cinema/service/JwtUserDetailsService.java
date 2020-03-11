package cinema.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cinema.dto.AccountDto;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	IAccountService accountService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		PasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
		
		Optional<AccountDto> optUsername = accountService.getAccountByUsername(username);
		
		if (optUsername.isPresent()) {
			String trueUsername = optUsername.get().getUsername();
			String encryptedPassword = pwdEncoder.encode(optUsername.get().getPassword());
			return new User(
					trueUsername,
					encryptedPassword,
					new ArrayList<>());

		} else {
			throw new UsernameNotFoundException("User not found with this username");
		}
	}

}
