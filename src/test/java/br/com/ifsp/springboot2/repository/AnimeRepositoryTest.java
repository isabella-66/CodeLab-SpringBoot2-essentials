package br.com.ifsp.springboot2.repository;

import br.com.ifsp.springboot2.domain.Anime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Testes for Anime Repository")
class AnimeRepositoryTest {
    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("Save creates anime when Successful")
    void save_PersistentAnime_WhenSuccessful() { //nomeDoMétodoQueVaiTestar_oQueVaiFazer_quandoVaiFazer
        //testando repositório - não tem convensão para rodar o teste
        Anime animeToBeSaved = createAnime();
        Anime savedAnime = this.animeRepository.save(animeToBeSaved);

        //asserções para saber se o anime foi salvo
        Assertions.assertThat(savedAnime).isNotNull();
        Assertions.assertThat(savedAnime.getId()).isNotNull();
        Assertions.assertThat(savedAnime.getName()).isEqualTo(animeToBeSaved.getName()); //se valor salvo é igual ao que pediu para ser salvo
    }

    private Anime createAnime() {
        return Anime.builder()
                .name("Hajine no Ippo")
                .build();
    }
}