package br.com.wa.http.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.wa.http.UserWS;
import br.com.wa.http.URLMapping;
import br.com.wa.http.domain.request.UsersRequest;
import br.com.wa.http.domain.request.UserRequest;
import br.com.wa.http.domain.response.UserListResponse;
import br.com.wa.http.domain.response.UserResponse;
import br.com.wa.usecase.user.ListUsers;
import br.com.wa.usecase.user.SaveUser;
import br.com.wa.usecase.user.SaveUsers;

@RestController
public class UserWSImpl implements UserWS {
	
	@Autowired
	private SaveUsers saveRegisters;
	
	@Autowired
	private SaveUser saveRegister;
	
	@Autowired
	private ListUsers listRegisters;

	@Override
	@PostMapping(value = URLMapping.POST_SAVE_REGISTER)
	public UserResponse saveRegister(@RequestBody UserRequest request) {
		return this.saveRegister.execute(request);
	}

	@Override
	@PostMapping(value = URLMapping.POST_SAVE_REGISTERS)
	public UserResponse saveRegisters(@RequestBody UsersRequest request) {
		return this.saveRegisters.execute(request);
	}

	@Override
	@GetMapping(value = URLMapping.GET_LIST_REGISTER)
	public UserListResponse listRegisters() {
		return this.listRegisters.execute();
	}

}
