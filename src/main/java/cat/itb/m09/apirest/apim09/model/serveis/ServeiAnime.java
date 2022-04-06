package cat.itb.m09.apirest.apim09.model.serveis;

import cat.itb.m09.apirest.apim09.model.entitats.Anime;
import cat.itb.m09.apirest.apim09.model.repositoris.RepositoriAnime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServeiAnime {
    private final RepositoriAnime repAnimu;


    // llistar tots els items
    public List<Anime> listAnimus(){
        return repAnimu.findAll();
    }

    // llista un item per id
    public Anime findAnime(Long id) {
        return repAnimu.findById(id).orElse(null);
    }

    // afegir un item
    public Anime addAnimu(Anime it) {
        return repAnimu.save(it);
    }

    // modificar un item sencer
    public Anime modifyAnimu(Anime anime) {
        Anime aux = null;
        if (repAnimu.existsById(anime.getIdAnime()))
            aux = repAnimu.save(anime);

        return aux;
    }

    // eliminar un item per id
    public Anime deleteAnimu(Long id) {
        Anime res = repAnimu.findById(id).orElse(null);
        if (res != null)
            repAnimu.deleteById(id);

        return res;
    }
}
