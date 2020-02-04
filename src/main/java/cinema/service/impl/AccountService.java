package cinema.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cinema.dto.AccountDto;
import cinema.persistence.entity.Account;
import cinema.persistence.repository.AccountRepository;
import cinema.service.IAccountService;
import utils.DtoUtils;

@Service
@Transactional
public class AccountService implements IAccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	ModelMapper mapper;
	
	
	@Override
	public List<AccountDto> getAllUsers() {
		return DtoUtils.listFromEntityStream(accountRepository.findAll().stream(), 
											mapper, AccountDto.class);
	}

	@Override
	public Set<AccountDto> showIfSomeoneIsLoggedIn() {
		Set<AccountDto> listOfLogged = accountRepository.findAll().stream()
										.filter(acc -> acc.getLogged() == true)
										.map(acc-> mapper.map(acc, AccountDto.class))
										.collect(Collectors.toSet());
		if (listOfLogged.isEmpty()) {
			System.out.println("No one is logged in currently.");
		}
		return listOfLogged;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account accountSaved = mapper.map(accountDto, Account.class);
		String username = accountSaved.getUsername();
		String email = accountSaved.getEmail();
		if (accountRepository.findByEmail(email).isPresent()) {
			System.out.println("L'email saisi est déjà lié à un compte.");
			return null;
		} else if (accountRepository.findByUsername(username).isPresent()) {
			System.out.println("Le username saisi n'est pas disponible. "
					 + "Veuillez saisir un username différent.");
			return null;
			} 
				accountRepository.save(accountSaved);
				mapper.map(accountSaved, accountDto);
				return accountDto;
	}

	@Override
	public Optional<AccountDto> loginAccount(String email, String password) {
		// TODO methode stream ?

		// methode traditionnelle
		Optional<Account> accountOpt = accountRepository.findByEmail(email);
		if (accountOpt.isPresent()) {
			if (accountOpt.get().getPassword().equals(password)) {
				accountOpt.get().setLogged(true);
				return Optional.of(mapper.map(accountOpt.get(), AccountDto.class));
			}
			System.out.println("The password is incorrect.");
			return Optional.empty();
		}
		System.out.println("The account has not been found in the repository.");
		return Optional.empty();
	}

	@Override
	public String logoutAccount(String username) {
		Optional<Account> accountOpt = accountRepository.findByUsername(username);
		if (accountOpt.isPresent()) {
			if (accountOpt.get().getLogged()==true) {
				accountOpt.get().setLogged(false);
				return "Vous êtes bien déconnecté·e du compte " + username;
			}	
			return "Le compte " + username + " est déjà déconnecté";
		}
		return "Impossible de vous déconnecter : le compte "
				+ username + " n'existe pas. Veuillez vérifier votre saisie." ;
	}

	@Override
	public Optional<AccountDto> setAccountAdmin(String username) {
		Optional<Account> accountOpt = accountRepository.findByUsername(username);
		accountOpt.ifPresent(acc-> {
			acc.setAdminRole(true);
			accountRepository.flush();
		});
		return Optional.of(mapper.map(accountOpt.get(), AccountDto.class));
	}
	
	@Override
	public Optional<AccountDto> setAccountNonAdmin(String username) {
		Optional<Account> accountOpt = accountRepository.findByUsername(username);
		accountOpt.ifPresent(acc-> {
			acc.setAdminRole(false);
			accountRepository.flush();
		});
		return Optional.of(mapper.map(accountOpt.get(), AccountDto.class));
	}
	
}
