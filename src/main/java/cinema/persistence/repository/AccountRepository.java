package cinema.persistence.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import cinema.persistence.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
	Set<Account> findByFirstName(String firstName);
	Set<Account> findByLastName(String lastName);
	Optional<Account> findByUsername(String username);
	Optional<Account> findByEmail(String email);
	Set<Account> findByIsAdmin(Boolean isAdmin);
	Set<Account> findByLogged(Boolean loggedIn);
	Optional<Account> findByIdUser(Integer idUser);
}
