package br.com.ifsp.springboot2.mapper;

import br.com.ifsp.springboot2.domain.Anime;
import br.com.ifsp.springboot2.requests.AnimePostRequestBody;
import br.com.ifsp.springboot2.requests.AnimePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AnimeMapper {
    public static final AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    //conversão automática para Anime dos atributos dos valores das classes
    public abstract Anime toAnime(AnimePostRequestBody animePostRequestBody);
    public abstract Anime toAnime(AnimePutRequestBody animePutRequestBody);

}
