package com.ithappens.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("01").roles("USER").and().withUser("admin")
				.password("01").roles("ADMIN", "USER");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/layout/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/ithappens").hasRole("USER").antMatchers("/ithappens/dashboard")
				.hasRole("USER").antMatchers("/ithappens/detalhes/**").hasRole("USER")
				.antMatchers("/ithappens/orderedItems/**").hasRole("USER").antMatchers("/ithappens/**").hasRole("ADMIN")
				.anyRequest().authenticated().and().formLogin().loginPage("/ithappens/login").permitAll().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/ithappens/logout")).and().exceptionHandling().and()
				.sessionManagement().invalidSessionUrl("/ithappens/login");
	}

}
