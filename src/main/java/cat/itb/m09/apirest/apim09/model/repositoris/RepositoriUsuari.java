package cat.itb.m09.apirest.apim09.model.repositoris;

import cat.itb.m09.apirest.apim09.model.entitats.Usuari;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoriUsuari extends JpaRepository<Usuari, Long> {
    Optional<Usuari> findByUsername(String username);

}
