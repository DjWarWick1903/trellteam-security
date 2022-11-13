package ro.trellteam.security.controller.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.trellteam.security.dto.AccountDto;
import ro.trellteam.security.dto.response.ObjectResponse;
import ro.trellteam.security.exceptions.TrellGenericException;
import ro.trellteam.security.service.v1.UserService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user/v1", produces = APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/main/account/{username}")
    public ResponseEntity<ObjectResponse> fetchAccount(@PathVariable String username) {
        log.debug("UserController--fetchAccount--IN");
        if(username == null || username.isEmpty()) {
            throw new TrellGenericException("SECURITY_ERR_3");
        }

        final AccountDto account = userService.fetchAccount(username);
        final ObjectResponse response = new ObjectResponse(account);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/main/account")
    public ResponseEntity<ObjectResponse> createAccount(@RequestBody @Valid AccountDto request) {
        log.debug("UserController--createAccount--IN");
        final AccountDto account = userService.createAccount(request);
        final ObjectResponse response = new ObjectResponse(account);
        final URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/v1/main/account").toUriString());
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/account")
    public ResponseEntity<ObjectResponse> getAccounts() {
        log.debug("UserController--getAccounts--IN");
        final List<AccountDto> accountDtos = userService.getAccounts();
        final ObjectResponse response = new ObjectResponse(accountDtos);
        return ResponseEntity.ok().body(response);
    }
}
