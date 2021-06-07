package br.com.wa.usecase.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import br.com.wa.domain.conveter.UserConverter;
import br.com.wa.domain.user.User;
import br.com.wa.domain.user.DataBaseSequence;
import br.com.wa.http.domain.request.UserRequest;
import br.com.wa.http.domain.response.UserResponse;
import br.com.wa.repository.facade.UserRepositoryFacade;
import br.com.wa.usecase.user.SaveUser;

@Component
public class SaveUserUseCase implements SaveUser {
	
	@Autowired
	private UserRepositoryFacade userRepositoryFacade;
	
	@Autowired
	private MongoOperations mongoOperations;
	
	@Override
	public UserResponse execute(UserRequest request) {
		int id = this.findNextSequence(User.SEQUENCE_NAME).getSequenceValue();
		User user = this.userRepositoryFacade.save(UserConverter.convertFromRequest(request, id));
		UserResponse response = new UserResponse(user);
		return response;
	}

	@Override
	public DataBaseSequence findNextSequence(String sequenceName) {
		return mongoOperations.findAndModify( Query.query(Criteria.where("_id").is(sequenceName)), new Update().inc("sequenceValue",1),
	            									FindAndModifyOptions.options().returnNew(true).upsert(true), DataBaseSequence.class);
	}

}