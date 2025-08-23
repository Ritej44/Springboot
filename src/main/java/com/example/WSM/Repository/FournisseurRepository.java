package com.example.WSM.Repository;

import com.example.WSM.Model.Fournisseur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FournisseurRepository extends MongoRepository<Fournisseur, String> {
}
