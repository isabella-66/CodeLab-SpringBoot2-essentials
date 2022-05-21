//apresentação banco de dados
package br.com.ifsp.springboot2.repository;

import br.com.ifsp.springboot2.domain.Anime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimeRepository extends JpaRepository<Anime, Long> {

}
