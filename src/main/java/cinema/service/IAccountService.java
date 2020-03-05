package cinema.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import cinema.dto.AccountDto;

public interface IAccountService {
	
	List<AccountDto> getAllUsers();
	Set<AccountDto> showIfSomeoneIsLoggedIn();
	AccountDto createAccount(AccountDto accountDto);
	Optional<AccountDto> loginAccount(String email, String password);
	String logoutAccount(String username);
	Optional<AccountDto> setAccountAdmin(String username);
	Optional<AccountDto> setAccountNonAdmin(String username);
	
	Optional<List<Integer>> getLikedMovies();
	

}
