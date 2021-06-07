package br.com.wa.amqp.listener.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.wa.amqp.domain.KafkaConstants;
import br.com.wa.amqp.domain.UserAmqpMessage;
import br.com.wa.amqp.listener.UserListener;
import br.com.wa.http.domain.request.UserRequest;
import br.com.wa.usecase.user.SaveUser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserListenerImpl implements UserListener {
	
	@Autowired
	private SaveUser saveRegister;

	@Override
	@KafkaListener(topics = KafkaConstants.TOPIC_REGISTER,
				   groupId = KafkaConstants.GROUP_ID,
				   containerFactory = KafkaConstants.FACTORY)
	public void consumeMessage(UserAmqpMessage message) {
		log.info("A new message arrived");
		log.info("{} Users to register", message.getRegisters().size());
		
		for(UserRequest userRequest : message.getRegisters()) {
			this.saveRegister.execute(userRequest);
		}
		
		log.info("Message Listener Complete");
	}
	 
}