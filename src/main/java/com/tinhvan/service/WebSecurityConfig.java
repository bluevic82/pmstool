package com.tinhvan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	MyDBAuthenticationService myAuService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myAuService);
	}

	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		// Các trang không yêu cầu login
		http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();

		// Trang /userInfo yêu cầu phải login với vai trò USER hoặc ADMIN.
		// Nếu chưa login, nó sẽ redirect tới trang /login.
		http.authorizeRequests().antMatchers("/**").authenticated();
	//	http.authorizeRequests().antMatchers("/welcome").authenticated();
		http.authorizeRequests().antMatchers("/").authenticated();
		
		
		
		
		// For ADMIN only.
		// Trang chỉ dành cho ADMIN
		http.authorizeRequests().antMatchers("/admin").access("hasRole('1')");

		// Khi người dùng đã login, với vai trò XX.
		// Nhưng truy cập vào trang yêu cầu vai trò YY,
		// Ngoại lệ AccessDeniedException sẽ ném ra.
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		// Cấu hình cho Login Form.
		http.authorizeRequests().and().formLogin()//

				// Submit URL của trang login
				.loginProcessingUrl("/j_spring_security_check") // Submit URL
				.loginPage("/login")//
				.defaultSuccessUrl("/")//
				.failureUrl("/?error=true")//
				.usernameParameter("user_mail")//
				.passwordParameter("user_password")

				// Cấu hình cho Logout Page.
				/*.and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");*/
				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login").deleteCookies("JSESSIONID")
				.invalidateHttpSession(true);
		
	//	http.formLogin().defaultSuccessUrl("/", true);
	}

}
