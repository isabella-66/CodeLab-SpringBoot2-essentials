package br.com.ifsp.springboot2.client;

import br.com.ifsp.springboot2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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
    }
}
