package org.generation.italy.demo.security;

import org.generation.italy.demo.serv.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
	
	@Bean
	public SecurityFilterChain getFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests()
				.requestMatchers("/*/create", "/*/create/*").hasAuthority("admin")
				.requestMatchers("/*/update", "/*/update/*").hasAuthority("admin")
				.requestMatchers("/*/delete", "/*/delete/*").hasAuthority("admin")
				.requestMatchers("/**").permitAll()
			.and().formLogin()
			.and().logout()
		;

		return http.build();
	}

	@Bean
	public UserDetailsService getuseDetailsService() {

		return new UserService();
	}
	@Bean
	public PasswordEncoder getPasswordEncoder() {

		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider getAuthProvider() {

		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

		provider.setUserDetailsService(getuseDetailsService());
		provider.setPasswordEncoder(getPasswordEncoder());

		return provider;
	}
}
