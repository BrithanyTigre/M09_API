package cat.itb.m09.apirest.apim09.seguretat.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginPassword {
    private String username;
    private String password;
}
