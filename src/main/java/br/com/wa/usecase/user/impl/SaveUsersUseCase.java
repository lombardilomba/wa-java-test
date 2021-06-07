package br.com.wa.usecase.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import br.com.wa.amqp.domain.UserAmqpMessage;
import br.com.wa.amqp.domain.KafkaConstants;
import br.com.wa.http.domain.request.UsersRequest;
import br.com.wa.http.domain.response.UserResponse;
import br.com.wa.usecase.user.SaveUsers;

@Component
public class SaveUsersUseCase implements SaveUsers {
	
    @Autowired
    private KafkaTemplate<String, UserAmqpMessage> kafkaTemplate;
    
    public UserResponse execute(UsersRequest request) {    	
    	kafkaTemplate.send(KafkaConstants.TOPIC_REGISTER, new UserAmqpMessage(request.getUsers()));
    	return new UserResponse(null);
    }

}
