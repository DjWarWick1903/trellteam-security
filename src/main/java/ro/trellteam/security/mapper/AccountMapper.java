package ro.trellteam.security.mapper;

import org.mapstruct.Mapper;
import ro.trellteam.security.domain.Account;
import ro.trellteam.security.dto.AccountDto;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountDto domainToDto(Account account);
    Account dtoToDomain(AccountDto accountDto);
}
