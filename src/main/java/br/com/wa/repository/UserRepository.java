package br.com.wa.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.wa.domain.user.User;

@Repository
public interface UserRepository extends MongoRepository<User, Integer> {

}
