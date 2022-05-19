//lógica de negócios

package br.com.ifsp.springboot2.service;

import br.com.ifsp.springboot2.domain.Anime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimeService {
    //private final AnimeRepository animeRepository
    public List<Anime> listAll() {
        return List.of(new Anime (1L, "DBZ"), new Anime (2L, "Berserk"));
    }
}
