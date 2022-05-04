package cat.itb.m09.apirest.apim09.model.entitats;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@RequiredArgsConstructor
public class Anime {
    @Id
    @GeneratedValue
    private Long idAnime;
    private String name;
    private String state;
    private int seasons;
    private String genre;

}
