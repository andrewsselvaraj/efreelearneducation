package com.efreelearn.education;



import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {

		// add users in List
		List<UserDetails> users = new ArrayList<UserDetails>();
		
		users.add(User.withUsername("web").
				password(passwordEncoder().encode("webarrow")).roles("USER").build());
		
		users.add(User.withUsername("andrew").
				password(passwordEncoder().encode("Admin@123#")).roles("ADMIN").build());
		
		//User.withDefaultPasswordEncoder().username("user").password("user").roles("USER").build(); 

		return new InMemoryUserDetailsManager(users);
	}
}