package br.com.ifsp.springboot2.controller;

import br.com.ifsp.springboot2.domain.Anime;
import br.com.ifsp.springboot2.service.AnimeService;
import br.com.ifsp.springboot2.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController //classe controller com retorno só de String
@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {
    private final DateUtil dateUtil;
    private final AnimeService animeService;

    //localhost:8080/anime/list
    @GetMapping
    public List<Anime> list() {
        log.info(dateUtil.formatLocalDateTimetoDatabaseStyle(LocalDateTime.now()));
        return animeService.listAll();
    }
}
