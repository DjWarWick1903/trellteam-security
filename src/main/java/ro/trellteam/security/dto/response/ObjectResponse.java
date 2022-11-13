package ro.trellteam.security.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ObjectResponse extends Response {
    private Object response;
}
