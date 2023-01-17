package com.crud.configuration;

import java.net.http.HttpRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMethodMappingNamingStrategy;

import jakarta.annotation.security.RolesAllowed;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails admin=User.withDefaultPasswordEncoder()
				.username("admin")
				.password("password")
				.roles("ADMIN")
				.build();
		UserDetails user=User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user, admin);
	}

	@Bean
	 public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		return http.authorizeHttpRequests().requestMatchers("/h2").permitAll()
		.requestMatchers("/api/users/**").hasRole("ADMIN")
		.requestMatchers("/api/users/**").hasRole("USER")
			.and().httpBasic().and().csrf().disable().build();
		
	}
}