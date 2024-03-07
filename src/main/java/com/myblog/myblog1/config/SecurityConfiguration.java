package com.myblog.myblog1.config;

        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;
        import org.springframework.http.HttpMethod;
        import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
        import org.springframework.security.config.annotation.web.builders.HttpSecurity;
        import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
        import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
        import org.springframework.security.core.userdetails.User;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
        import org.springframework.security.crypto.password.PasswordEncoder;
        import org.springframework.security.provisioning.InMemoryUserDetailsManager;
 @Configuration
 @EnableWebSecurity
 @EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    protected void configure(HttpSecurity http)throws Exception{
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/auth/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
    @Override
    @Bean
    protected UserDetailsService userDetailsService(){  /*This method is responsible to store objects in which
     i want to store username and password */
        UserDetails user1= User.builder().username("pankaj").password(passwordEncoder().encode("password"))
                .roles("USER").build();
        UserDetails admin= User.builder().username("admin").password(passwordEncoder().encode("admin"))
                .roles("ADMIN").build();

        return new InMemoryUserDetailsManager(user1,admin);
    }
}
/* csrf() :-"cross site Request Forgery" protection is a security features that helps prevent attackers from
executing unauthorized actions on behalf of an authenticated user.*/