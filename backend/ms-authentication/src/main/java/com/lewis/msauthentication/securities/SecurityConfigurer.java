package com.lewis.msauthentication.securities;

import com.lewis.msauthentication.filters.JWTAuthenticationFilter;
import com.lewis.msauthentication.services.MyEmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.servlet.http.HttpServletResponse;
import static com.lewis.msauthentication.filters.SecurityConstants.SIGN_UP_URL;


@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyEmployeeDetailsService myEmployeeDetailsService;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(myEmployeeDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.cors().and().httpBasic().and().csrf().disable().authorizeRequests();

        http.authorizeRequests()
                .antMatchers("/").permitAll().anyRequest().permitAll()
//                .antMatchers(SIGN_UP_URL).permitAll()
//                .anyRequest().authenticated()
                .and()
            //    .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .sessionManagement();
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .exceptionHandling().authenticationEntryPoint(
//                        (request, response, ex) ->
//                        {
//                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
//                        }
//                );
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder getpasswordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }
}
