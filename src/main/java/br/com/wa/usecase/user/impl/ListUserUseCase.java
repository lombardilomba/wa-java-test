package br.com.wa.usecase.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.wa.http.domain.response.UserListResponse;
import br.com.wa.repository.facade.UserRepositoryFacade;
import br.com.wa.usecase.user.ListUsers;

@Component
public class ListUserUseCase implements ListUsers {
	
	@Autowired
	private UserRepositoryFacade userRepositoryFacade;
	
	@Override
    public UserListResponse execute() {
    	return new UserListResponse(this.userRepositoryFacade.findAll());
    }

}
