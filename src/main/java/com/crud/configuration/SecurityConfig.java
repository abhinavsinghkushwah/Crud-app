package com.crud.configuration;

import java.net.http.HttpRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMethodMappingNamingStrategy;

import jakarta.annotation.security.RolesAllowed;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	PasswordEncoder encoder;
	
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails admin=User.builder()
				.username("admin")
				.password(encoder.encode("password"))
				.roles("ADMIN")
				.build();
		UserDetails user=User.builder()
				.username("user")
				.password(encoder.encode("password"))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user, admin);
	}

	@Bean
	 public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		return http.httpBasic().and().csrf().disable()
				.formLogin().loginProcessingUrl("/login").and()
				.authorizeHttpRequests().requestMatchers("/login").permitAll()
			.anyRequest().authenticated()
			.and().build();
		
	}
}