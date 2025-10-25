package co.edu.unbosque.retazoTextil.security;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

//	private final UsuarioRepository userRepository;
//
//	public UserDetailsServiceImpl(UsuarioRepository userRepository) {
//		this.userRepository = userRepository;
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String username) {
//		String encrypted = AESUtil.encrypt(username);
//		Usuario user = userRepository.findByUsername(encrypted)
//				.orElseThrow(() -> new UsernameNotFoundException("Usuario no hallado: " + username));
//
//		user.setUsername(username);
//		user.setEmail(AESUtil.decrypt(user.getEmail()));
//		return user;
//	}
}
