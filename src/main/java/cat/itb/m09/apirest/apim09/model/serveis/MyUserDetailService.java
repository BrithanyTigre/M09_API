package cat.itb.m09.apirest.apim09.model.serveis;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    private final ServeiUsuari serveiUsuariUserDetails;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return serveiUsuariUserDetails.consultarPerUsername(username);
    }

    public UserDetails loadUserById(Long id){
        return serveiUsuariUserDetails.consultarPerId(id);
    }
}
