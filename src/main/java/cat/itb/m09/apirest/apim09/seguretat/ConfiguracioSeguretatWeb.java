package cat.itb.m09.apirest.apim09.seguretat;

import cat.itb.m09.apirest.apim09.model.serveis.MyUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfiguracioSeguretatWeb extends WebSecurityConfigurerAdapter {

    private final MyAuthenticationEntryPoint myEntryPoint;
    private final MyUserDetailService myUserDeatilService;
    private final PasswordEncoder xifrat;

    //Per fer proves al principi, per poder fer post i put d'usuaris sense seguretat
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().anyRequest();
//    }



//codi per fer una prova autenticant en memòria "inMemoryAuthentication()"
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .passwordEncoder(xifrat)
//                .withUser("Montse")
//                .password(xifrat.encode("secret"))
//                .roles("ADMIN"); // és necessari posar tots els camps, fins el rol (authorities)
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDeatilService).passwordEncoder(xifrat);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .httpBasic()
                .authenticationEntryPoint(myEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/me/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/users/**", "/animu/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/users/**", "/animu/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/animu/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/animu/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/animu/**").hasAnyRole("USER", "ADMIN")
                .anyRequest().authenticated();
        //      .and()
        //      .csrf().disable();
    }
}