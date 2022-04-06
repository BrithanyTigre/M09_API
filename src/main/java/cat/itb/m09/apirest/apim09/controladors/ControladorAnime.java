package cat.itb.m09.apirest.apim09.controladors;

import cat.itb.m09.apirest.apim09.model.entitats.Anime;
import cat.itb.m09.apirest.apim09.model.serveis.ServeiAnime;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ControladorAnime {

    private final ServeiAnime serveiAnimu;

    @GetMapping("/animus")
    public ResponseEntity<?> listAnimus() {
        List<Anime> animus = serveiAnimu.listAnimus();
        if (animus.size() == 0)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(animus);
    }

    @GetMapping("/animus/{id}")
    public ResponseEntity<?> getAnimu(@PathVariable Long id) {
        Anime animu = serveiAnimu.findAnime(id);
        if (animu == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(animu);
    }

    @PostMapping("/animus")
    public ResponseEntity<?> addAnimu(@RequestBody Anime anime) {
        Anime res = serveiAnimu.addAnimu(anime);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/animus")
    public ResponseEntity<?> modifyAnimu(@RequestBody Anime anime) {
        Anime res = serveiAnimu.modifyAnimu(anime);

        if (res == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok(res);
    }

    @DeleteMapping("/animus/{id}")
    public ResponseEntity<?> deleteAnimu(@PathVariable Long id) {
        Anime res = serveiAnimu.deleteAnimu(id);
        if (res == null)
            return ResponseEntity.notFound().build();
        else
            return new ResponseEntity<>(res, HttpStatus.NO_CONTENT);
    }
}
