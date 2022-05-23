package br.com.ifsp.springboot2.repository;

import br.com.ifsp.springboot2.domain.Anime;
import br.com.ifsp.springboot2.domain.DevDojoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DevDojoUserRepository extends JpaRepository<DevDojoUser, Long> {
    DevDojoUser findByUsername(String username);
}
