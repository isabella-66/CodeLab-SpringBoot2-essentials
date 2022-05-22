package br.com.ifsp.springboot2.integration;

import br.com.ifsp.springboot2.domain.Anime;
import br.com.ifsp.springboot2.repository.AnimeRepository;
import br.com.ifsp.springboot2.util.AnimeCreator;
import br.com.ifsp.springboot2.wrapper.PageableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;

//pode ter aplicação rodando na porta 8080, então passa porta aleatória todas as vezes que esses testes forem executados
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class AnimeControllerIT {
    @Autowired
    private TestRestTemplate testRestTemplate; //acha a porta inicializada
    @LocalServerPort
    private int port; //outra forma de achar a porta
    @Autowired
    private AnimeRepository animeRepository;

    @Test
    @DisplayName("List returns list of anime inside page object when successful")
    void list_ReturnsListOfAnimeInsidePageObject_WhenSuccessful() {
        //executa teste, que cria valor no BD de teste (banco de memórias), executa requisição no controller,
        //que retorna um PageableResponse, que dentro tem a lista de animes
        Anime savedAnime = animeRepository.save(AnimeCreator.createAnimeToBeSaved());
        String expectedName = savedAnime.getName();

        PageableResponse<Anime> animePage = testRestTemplate.exchange("/animes", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Anime>>() {
                }).getBody();

        Assertions.assertThat(animePage).isNotNull();
        Assertions.assertThat(animePage.toList())
                .isNotEmpty()
                .hasSize(1); //retornando lista com apenas um valor
        Assertions.assertThat(animePage.toList().get(0).getName()).isEqualTo(expectedName);
    }
}
