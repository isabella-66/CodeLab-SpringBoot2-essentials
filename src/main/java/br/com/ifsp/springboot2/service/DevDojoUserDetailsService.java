package br.com.ifsp.springboot2.service;

import br.com.ifsp.springboot2.repository.DevDojoUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DevDojoUserDetailsService implements UserDetailsService {
    private final DevDojoUserRepository devDojoUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return Optional.ofNullable(devDojoUserRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("DevDojo User not found"));
    }
}
