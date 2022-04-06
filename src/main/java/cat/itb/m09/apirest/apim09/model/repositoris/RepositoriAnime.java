package cat.itb.m09.apirest.apim09.model.repositoris;

import cat.itb.m09.apirest.apim09.model.entitats.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoriAnime extends JpaRepository<Anime, Long> {
       
}
