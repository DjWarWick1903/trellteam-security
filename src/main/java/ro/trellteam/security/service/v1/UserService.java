package ro.trellteam.security.service.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.trellteam.security.domain.Account;
import ro.trellteam.security.dto.AccountDto;
import ro.trellteam.security.mapper.AccountMapper;
import ro.trellteam.security.repository.AccountRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final AccountRepositoryImpl accountRepository;
    private final AccountMapper accountMapper;

    public AccountDto fetchAccount(final String username) {
        log.debug("UserService--fetchAccount--username: {}", username);
        final Account account = accountRepository.getAccount(username);
        return accountMapper.domainToDto(account);
    }

    public AccountDto createAccount(final AccountDto request) {
        log.debug("UserService--createAccount--request: {}", request);
        final Account account = accountRepository.save(accountMapper.dtoToDomain(request), true);
        return accountMapper.domainToDto(account);
    }

    public List<AccountDto> getAccounts() {
        log.debug("UserService--createAccount--IN");
        final List<Account> accounts = accountRepository.list();
        final List<AccountDto> accountDtos = accounts.stream()
                .map(a -> accountMapper.domainToDto(a))
                .collect(Collectors.toList());
        return accountDtos;
    }
}
