package br.com.ifsp.springboot2.client;

import br.com.ifsp.springboot2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {
        //requisição para próprio serviço 8080:
        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/{id}",
                Anime.class, 1); //getBody() retornaria o anime em si
        log.info(entity);

        Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/{id}",
                Anime.class, 1); //conforme adiciona id, Spring coloca na ordem informada
        log.info(object);

        Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all",
                Anime[].class); //mapeamento automático dos dados de uma lista
        log.info(Arrays.toString(animes));

        //@formatter:off
        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}); //Array desatualizado, conversão para lista - geração automática
        //@formatter:on
        log.info(exchange.getBody()); //valid ResponseEntity

//        Anime kingdom = Anime.builder().name("kingdom").build(); //criando objeto
//        Anime kingdomSaved = new RestTemplate().postForObject("http://localhost:8080/animes/all", kingdom,
//                Anime.class); //no Controller retorna o próprio objeto
//        log.info("Saved anime {}", kingdomSaved);

        Anime samuraiChamploo = Anime.builder().name("Samurai Champloo").build();
        ResponseEntity<Anime> samuraiChamplooSaved = new RestTemplate().exchange("http://localhost:8080/animes/all",
                HttpMethod.GET,
                new HttpEntity<>(samuraiChamploo),
                Anime.class); //getBody() retorna diretamente
        log.info("Saved anime {}", samuraiChamplooSaved); //exchange poderoso - pode mandar headers http dentro do HttpEntity
    }

    //enviando header dizendo que content type dessa requisição é um aplication JSON
    private static HttpHeaders createJsonHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
