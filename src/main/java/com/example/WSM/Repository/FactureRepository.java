package com.example.WSM.Repository;



import com.example.WSM.Model.Facture;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactureRepository extends MongoRepository<Facture, String> {

    // Trouver les factures non payées (note ne contenant pas "PAYE")
    List<Facture> findByNoteNotContainingIgnoreCase(String keyword);

    @Query("{ 'NOTE': { $regex: ?0, $options: 'i' } }")
    List<Facture> findByNoteContaining(String keyword);

    default List<Facture> findFacturesWithPaye() {
        return findByNoteContaining("PAYE");
    }
    default List<Facture> findFacturesNOTPaye() {
        return findByNoteNotContainingIgnoreCase("PAYE");
    }

    // Trouver les factures par fournisseur
    List<Facture> findByIdFournisseur(String idFournisseur);

    // Version plus précise avec query personnalisée
    @Query("{ $or: [ { 'NOTE': { $exists: false } }, { 'NOTE': null }, { 'NOTE': '' }, { 'NOTE': { $not: /PAYE/i } } ] }")
    List<Facture> findFacturesNonPayees();

    // Alternative avec regex plus robuste
    @Query("{ $or: [ { 'NOTE': { $exists: false } }, { 'NOTE': null }, { 'NOTE': '' }, { 'NOTE': { $not: /PAYE|PAYÉ|PAID/i } } ] }")
    List<Facture> findNonPayeesRobuste();
}

