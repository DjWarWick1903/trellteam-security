package ro.trellteam.security.controller.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.trellteam.security.service.v1.SecurityService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/security/v1", produces = APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class SecurityController {
    private final SecurityService securityService;

    @GetMapping("/token/refresh")
    public void refreshToken(final HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.debug("SecurityController--refreshToken--IN");
        securityService.refreshToken(request, response);
    }
}
