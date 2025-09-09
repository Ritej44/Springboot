package com.example.WSM.Repository;

import com.example.WSM.Model.Fournisseur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FournisseurRepository extends MongoRepository<Fournisseur, String> {

    List<Fournisseur> findByNom(String nom);
    Fournisseur findNomById (String id);

}
