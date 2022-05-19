//apresentação banco de dados
package br.com.ifsp.springboot2.repository;

import br.com.ifsp.springboot2.domain.Anime;

import java.util.List;

public interface AnimeRepository {
    List<Anime> listAll();
}
