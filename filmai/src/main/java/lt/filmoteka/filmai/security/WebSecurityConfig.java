package lt.filmoteka.filmai.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/*").authenticated()
                .antMatchers("/kategorija/pridejimas").hasAnyRole("ROLE_ADMIN")
                .antMatchers("/kategorija/redagavimas/*").hasAnyRole("ADMIN")
                .antMatchers("/kategorija/istrinti/*").hasAnyRole("ADMIN")
                .antMatchers("/kategorija/pridetas").hasAnyRole("ADMIN")
                .antMatchers("/filmas/pridejimas").hasAnyRole("ADMIN")
                .antMatchers("/filmas/redagavimas/*").hasAnyRole("ADMIN")
                .antMatchers("/filmas/pridetas").hasAnyRole("ADMIN")
                .antMatchers("/filmas/istrinti/*").hasAnyRole("ADMIN")
                .and()
                .formLogin()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/errors/403")
                .and()
                .httpBasic()
                .and()
                .logout();
        return http.build();
    }
}

