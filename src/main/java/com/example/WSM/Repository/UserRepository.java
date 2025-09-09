package com.example.WSM.Repository;

import com.example.WSM.Model.Paiement;
import com.example.WSM.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

 @Repository
public interface UserRepository extends MongoRepository<User, String> {

     Optional<User> findOneByEmailAndPassword(String email, String password);
      User findByEmail(String email);
}
