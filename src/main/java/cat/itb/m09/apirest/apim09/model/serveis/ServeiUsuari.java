package cat.itb.m09.apirest.apim09.model.serveis;

import cat.itb.m09.apirest.apim09.model.entitats.Usuari;
import cat.itb.m09.apirest.apim09.model.repositoris.RepositoriUsuari;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServeiUsuari {

    private final RepositoriUsuari repositoriUsuari;
    private final PasswordEncoder xifrat;

    public Usuari consultarPerId(Long id){
        return repositoriUsuari.findById(id).orElse(null);
    }

    public Usuari consultarPerUsername(String username) {
        return repositoriUsuari.findByUsername(username).orElse(null);
    }

    public Usuari addUser(Usuari usuari) {
        usuari.setPassword(xifrat.encode(usuari.getPassword()));
        repositoriUsuari.save(usuari);
        return usuari;
    }

    public List<Usuari> llistarUsuaris(){
        return repositoriUsuari.findAll();
    }
}
