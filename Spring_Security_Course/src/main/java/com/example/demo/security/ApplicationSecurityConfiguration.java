package com.example.demo.security;

import com.example.demo.auth.ApplicationUserService;
import com.example.demo.jwt.JwtTokenVerifier;
import com.example.demo.jwt.JwtConfig;
import com.example.demo.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.example.demo.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    @Autowired
    public ApplicationSecurityConfiguration(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService, JwtConfig jwtConfig, SecretKey secretKey) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
               .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                .addFilterAfter(new JwtTokenVerifier(), JwtUsernameAndPasswordAuthenticationFilter.class)
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
//                .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
                .anyRequest()
                .authenticated();
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .passwordParameter("password") // this is the default value
//                .usernameParameter("username") // this is the default value
//                .permitAll();
//                .defaultSuccessUrl("/courses", true)
//                .and()
//                .rememberMe()
//                .rememberMeParameter("remember-me") // default value
//                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
//                .key("somethingverysecured")
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET")) // case when csrf is disabled!
//                .logoutUrl("/logout")
//                .clearAuthentication(true)
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID", "remember-me")
//                .logoutSuccessUrl("/login");
//                .httpBasic();

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);

        return provider;
    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails annaSmithUser = User.builder()
//                .username("annasmith")
//                .password(passwordEncoder.encode("password"))
////                .roles(STUDENT.name()) //ROLE_STUDENT
//                .authorities(STUDENT.getGratedAuthorities())
//                .build();
//
//        UserDetails lindaUser = User.builder()
//                .username("Linda")
//                .password(passwordEncoder.encode("password123"))
////                .roles(ADMIN.name()) //ROLE_ADMIN
//                .authorities(ADMIN.getGratedAuthorities())
//                .build();
//
//        UserDetails tomUser = User.builder()
//                .username("Tom")
//                .password(passwordEncoder.encode("password"))
////                .roles(ADMINTRAINEE.name()) //ROLE_ADMINTRAINEE
//                .authorities(ADMINTRAINEE.getGratedAuthorities())
//                .build();
//
//        return new InMemoryUserDetailsManager(
//                annaSmithUser,
//                lindaUser,
//                tomUser
//        );
//    }

}
