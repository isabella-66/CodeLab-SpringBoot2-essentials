//todos os objetos utilizados nas classes de teste

package br.com.ifsp.springboot2.util;

import br.com.ifsp.springboot2.domain.Anime;

public class AnimeCreator {

    public static Anime createAnimeToBeSaved() { //sem id
        return Anime.builder()
                .name("Hajine no Ippo")
                .build();
    }

    public static Anime createValidAnime() { //tem id valid
        return Anime.builder()
                .name("Hajine no Ippo")
                .id(1L)
                .build();
    }

    public static Anime createValidUpdatedAnime() { //mesmo id, nome diferente / static pois não serão alterados
        return Anime.builder()
                .name("Hajine no Ippo s3")
                .id(1L)
                .build();
    }
}
