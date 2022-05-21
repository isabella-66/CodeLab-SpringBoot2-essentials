//lógica de negócios

package br.com.ifsp.springboot2.service;

import br.com.ifsp.springboot2.domain.Anime;
import br.com.ifsp.springboot2.repository.AnimeRepository;
import br.com.ifsp.springboot2.requests.AnimePostRequestBody;
import br.com.ifsp.springboot2.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor //Spring injeta dependências do animeRepository
public class AnimeService {
    private final AnimeRepository animeRepository;
    public List<Anime> listAll() {
        return animeRepository.findAll();
    }

    public Anime findByIdOrThrowBadRequestException(Long id) { //ou encontra id ou lança exceção
        return animeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
    }

    public Anime save(AnimePostRequestBody animePostRequestBody) {
        //salva o objeto (anime) e retorna com um id
        return animeRepository.save(Anime.builder().name(animePostRequestBody.getName()).build());
    }

    public void delete(long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id)); //se tentar remover anime, verifica se existe e remove da lista
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = Anime.builder()
                .id(savedAnime.getId()) //certeza que id é o que está no BD
                .name(savedAnime.getName())
                .build();
        animeRepository.save(anime);

    }
}
