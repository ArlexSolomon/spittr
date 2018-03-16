package spittr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import spittr.role.repositories.SpitterRepository;
import spittr.security.SpitterUserService;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = { spittr.security.Package.class })
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// @Autowired
	// private MyAuthenticationProvider provider;//自定义验证
	//
	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws Exception
	// {
	// auth.authenticationProvider(provider);
	// }

	@Autowired
	private SpitterRepository spitterRepository;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new SpitterUserService(spitterRepository));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").loginProcessingUrl("/login.action").defaultSuccessUrl("/")
				.usernameParameter("username").passwordParameter("password").permitAll().and().logout()
				.logoutUrl("/logout").logoutSuccessUrl("/login?loggedOut").invalidateHttpSession(true)
				.deleteCookies("JSESSIONID").permitAll().and().csrf().disable();
	}
	
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
        		"/js/**",
        		"/css/**",
                "/node_modules/**",
                "/register",
                "/rest/spitter/**"
        );
    }
}
