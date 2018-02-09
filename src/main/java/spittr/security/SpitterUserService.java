package spittr.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import spittr.role.entity.SpitterEntity;
import spittr.role.repositories.SpitterRepository;

public class SpitterUserService implements UserDetailsService {

	private final SpitterRepository spitterRepository;

	public SpitterUserService(SpitterRepository spitterRepository) {
		this.spitterRepository = spitterRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SpitterEntity spitter = spitterRepository.findByUserName(username);
		if (spitter == null) {
			throw new UsernameNotFoundException("User not found");
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_SPITTER"));
		return new User(spitter.getUserName(), spitter.getPassword(), authorities);
	}
}
