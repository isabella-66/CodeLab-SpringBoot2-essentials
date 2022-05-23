package br.com.ifsp.springboot2.config;

import br.com.ifsp.springboot2.service.DevDojoUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@SuppressWarnings("java S5344")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final DevDojoUserDetailsService devDojoUserDetailsService;

    /**
     * BasicAuthenticationFilter
     * UsernamePasswordAuthenticationFilter
     * DefaultLoginPageGeneratingFilter
     * DefaultLogoutPageGeneratingFilter
     * FilterSecurityInterceptor
     * Authentication -> Authorization
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //todas as requisições, URLs, que tem o controller, precisarão passar por autenticação básica
        http.csrf().disable()
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .authorizeRequests() //o que quer proteger
                .anyRequest() //todas as requisições
                .authenticated() //requisições autenticadas
                .and()
                .formLogin() //página login padrão
                .and()
                .httpBasic(); //forma de autenticação
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //criação do usuário em memória e tipo de criptografia para sua senha
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        log.info("Password Encoded: {}", passwordEncoder.encode("academy"));

        auth.inMemoryAuthentication()
                .withUser("isabella2")
                .password(passwordEncoder.encode("academy"))
                .roles("USER", "ADMIN")
                .and()
                .withUser("devdojo2")
                .password(passwordEncoder.encode("academy"))
                .roles("USER");

        auth.userDetailsService(devDojoUserDetailsService)
                .passwordEncoder(passwordEncoder); //padrão por toda a parte de segurança do Spring
    }
}
