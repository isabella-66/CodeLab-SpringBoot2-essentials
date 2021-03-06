//lógica de negócios

package br.com.ifsp.springboot2.service;

import br.com.ifsp.springboot2.domain.Anime;
import br.com.ifsp.springboot2.exception.BadRequestException;
import br.com.ifsp.springboot2.mapper.AnimeMapper;
import br.com.ifsp.springboot2.repository.AnimeRepository;
import br.com.ifsp.springboot2.requests.AnimePostRequestBody;
import br.com.ifsp.springboot2.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor //Spring injeta dependências do animeRepository
public class AnimeService {
    private final AnimeRepository animeRepository;
    public Page<Anime> listAll(Pageable pageable) {
        return animeRepository.findAll(pageable);
    }
    public List<Anime> listAllNonPageable() { return animeRepository.findAll(); } //para não manipular objeto do JSON, o content
    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public Anime findByIdOrThrowBadRequestException(Long id) { //ou encontra id ou lança exceção
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not found"));
    }

    @Transactional
    public Anime save(AnimePostRequestBody animePostRequestBody) {
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
    }

    public void delete(long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id)); //se tentar remover anime, verifica se existe e remove da lista
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
        anime.setId(savedAnime.getId());
        animeRepository.save(anime);
    }
}
