package br.com.wa.repository.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.wa.domain.user.User;
import br.com.wa.repository.UserRepository;
import br.com.wa.repository.facade.UserRepositoryFacade;

@Component
public class UserRepositoryFacadeImpl implements UserRepositoryFacade {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return this.userRepository.findAll();
	}
	
}
