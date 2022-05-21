//apresentação banco de dados
package br.com.ifsp.springboot2.repository;

import br.com.ifsp.springboot2.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
    List<Anime> findByName(String name); //retorna lista de animes, achando através do nome
}
