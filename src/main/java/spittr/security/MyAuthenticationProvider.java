package spittr.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import spittr.role.domain.Spitter;
import spittr.role.service.SpitterService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class MyAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private SpitterService spitterService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		Spitter account = spitterService.findByUserName(username);
		UserDetails userDetails = buildUserFromAccount(account);
		if (userDetails == null) {
			throw new UsernameNotFoundException("Username not found.");
		}
		if (password.equals(userDetails.getPassword())) {
			throw new BadCredentialsException("Wrong password.");
		}
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

	private User buildUserFromAccount(Spitter account) {
		if (null == account) {
			return null;
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("π‹¿Ì‘±"));
		User user = new User(account.getUserName(), account.getPassword(), authorities);
		return user;
	}
}