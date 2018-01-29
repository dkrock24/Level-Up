package com.focus.levelup.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    @Qualifier("datasource")
    private DataSource dataSource;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .authoritiesByUsernameQuery("SELECT u.email, r.role FROM users u JOIN roles r ON (u.id_role = r.id_role) WHERE u.status = 1 AND u.email=?")
            .usersByUsernameQuery("SELECT u.email, u.password, u.status FROM users u where u.email=?")
            .passwordEncoder(bCryptPasswordEncoder);
    }

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/**").permitAll();

/*		http.authorizeRequests()
			.antMatchers("/login", "/logout", "/", "/resources/**").permitAll();

		http.authorizeRequests()
			.antMatchers("/Roles**").access("hasRole('Admin')");

		http.authorizeRequests().and().formLogin()
			.loginProcessingUrl("/login")
			.loginPage("/")
			.defaultSuccessUrl("/loginsuccess")
			.failureUrl("/login?error=true")
			.usernameParameter("email")
			.passwordParameter("password")
			.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");*/
	}
}
