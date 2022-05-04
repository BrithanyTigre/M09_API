package cat.itb.m09.apirest.apim09.controladors;

import cat.itb.m09.apirest.apim09.model.entitats.Usuari;
import cat.itb.m09.apirest.apim09.model.entitats.UsuariConsultaDTO;
import cat.itb.m09.apirest.apim09.model.serveis.ServeiUsuari;
import cat.itb.m09.apirest.apim09.seguretat.jwt.JWTProvider;
import cat.itb.m09.apirest.apim09.seguretat.jwt.LoginPassword;
import cat.itb.m09.apirest.apim09.seguretat.jwt.UsuariJwt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ControladorRegistreLogUsuaris {
    private final ServeiUsuari serveiUsuari;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<UsuariJwt> login(@RequestBody LoginPassword userPassword) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userPassword.getUsername(), userPassword.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        Usuari user = (Usuari) auth.getPrincipal();
        String jwtToken = tokenProvider.generateToken(auth);
        UsuariJwt user2 = new UsuariJwt(user.getUsername(), user.getAvatar(), user.getRol(), jwtToken);

        return ResponseEntity.status(HttpStatus.CREATED).body(user2);
    }

    @GetMapping("/login")
    public UsuariConsultaDTO login(@AuthenticationPrincipal Usuari user) {
        return  new UsuariConsultaDTO(user.getUsername(), user.getAvatar(), user.getRol());
    }

    // Basic auth
    /*@GetMapping("/me")
    public UsuariConsultaDTO consultaQuiEts(@AuthenticationPrincipal Usuari usuari) {
        return new UsuariConsultaDTO(usuari.getUsername(), usuari.getAvatar(), usuari.getRol());
    }

    @PostMapping("/usuaris")
    public ResponseEntity<?> newUser(@RequestBody Usuari usuari) {
        try {
            Usuari res = serveiUsuari.addUser(usuari);
            UsuariConsultaDTO usu = new UsuariConsultaDTO(res.getUsername(), res.getAvatar(), res.getRol());
            return new ResponseEntity<UsuariConsultaDTO>(usu, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/usuaris")
    public ResponseEntity<?> listUsersDTO() {
        List<Usuari> res = serveiUsuari.llistarUsuaris();
        List<UsuariConsultaDTO> aux = new ArrayList();
        for (Usuari u : res) {
            aux.add(new UsuariConsultaDTO(u.getUsername(), u.getAvatar(),u.getRol()));
        }

        if (res.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else
            return ResponseEntity.ok(aux);
    }*/
}
