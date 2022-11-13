package ro.trellteam.security.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ro.trellteam.security.domain.Account;
import ro.trellteam.security.domain.Role;
import ro.trellteam.security.exceptions.TrellGenericException;

import javax.transaction.Transactional;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountRepositoryImpl {
    private final AccountRepository accountRepository;
    private final RoleRepositoryImpl roleRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Method used to save the account into the database.
     * @param account
     * @return Account
     */
    public Account save(Account account, boolean isNewAccount) {
        log.info("AccountService--save--IN");
        if(isNewAccount) account.setPassword(passwordEncoder.encode(account.getPassword()));
        account = accountRepository.save(account);
        log.info("AccountService--save--OUT");

        return account;
    }

    /**
     * Method used to add a new role to an account.
     * @param username
     * @param roleName
     */
    @Transactional
    public void addRoleToAccount(final String username, final String roleName) {
        log.info("AccountService--addRoleToAccount--IN");
        log.info("AccountService--addRoleToAccount--roleName: {}", roleName);
        log.info("AccountService--addRoleToAccount--username: {}", username);
        Account account = accountRepository.findByUsername(username);
        final Role role = roleRepository.findByName(roleName);
        account.getRoles().add(role); //because we have the transactional annot, once the method finishes it will save into db
        save(account, false);
        log.info("AccountService--addRoleToAccount--OUT");
    }

    /**
     * Method used to get an account starting from a username.
     * @param username
     * @return Account
     */
    public Account getAccount(final String username) {
        log.info("AccountService--getAccount--IN");
        log.info("AccountService--getAccount--username: {}", username);
        Account account = null;
        try {
            account = accountRepository.findByUsername(username);
        } catch(Exception e) {
            log.error(e.getMessage());
            throw new TrellGenericException("TRELL_ERR_1");
        }
        log.info("AccountService--getAccount--OUT");
        return account;
    }

    /**
     * Method used to return all Accounts
     * @return List
     */
    public List<Account> list() {
        log.info("AccountService--list--IN");
        return accountRepository.findAll();
    }

    /**
     * Method used to delete an account
     * @param account
     */
    public void delete(Account account) {
        log.info("AccountService--delete--IN");
        log.info("AccountService--delete--username: {}" , account.getUsername());
        accountRepository.deleteById(account.getId());
        log.info("AccountService--delete--OUT");
    }
}
