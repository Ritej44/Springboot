package com.example.WSM.Repository;

import com.example.WSM.Model.Fournisseur;
import com.example.WSM.Model.Paiement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaiementRepository extends MongoRepository<Paiement, String> {


}
