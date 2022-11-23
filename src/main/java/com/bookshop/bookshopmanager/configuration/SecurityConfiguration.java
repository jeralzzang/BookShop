package com.bookshop.bookshopmanager.configuration;

import com.bookshop.bookshopmanager.member.sign.service.MemberSignService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserAuthenticationSuccessHandler userAuthenticationSuccessHandler;
    @Bean
    UserAuthenticationFailureHandler getFailureHandler() {
        return new UserAuthenticationFailureHandler();
    }
    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final MemberSignService memberSignService;

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/favicon.ico", "/files/**");
//
//        super.configure(web);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();

        http.authorizeRequests()
            .antMatchers(
                "/"
                , "/member/signup.do",
                "/addbook"
            )
            .permitAll();

        http.authorizeRequests()
            .antMatchers("/admin/**")
            .hasAuthority("ROLE_ADMIN");

        http.formLogin()
            .loginPage("/member/signin.do")
            .failureHandler(getFailureHandler())
            .successHandler(userAuthenticationSuccessHandler)
            .permitAll();

        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/member/signout.do"))
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true);

//        http.exceptionHandling()
//            .accessDeniedPage("/error/denied");

        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberSignService)
            .passwordEncoder(getPasswordEncoder());

        super.configure(auth);
    }

}
