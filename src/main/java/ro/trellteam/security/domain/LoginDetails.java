package ro.trellteam.security.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDetails {
    private String access_token;
    private String refresh_token;
    private List<String> roles;
}
