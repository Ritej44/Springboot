package com.example.WSM.Service;

import com.example.WSM.Model.Facture;
import com.example.WSM.Repository.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FactureService {

    @Autowired
    private FactureRepository factureRepository;

    public List<Facture> findAll() {
        return factureRepository.findAll();
    }


    // Méthode pour trouver les factures non payées
    public List<Facture> findNonPayees() {
        return factureRepository.findByNoteNotContainingIgnoreCase("PAYE");
    }

    // Méthode améliorée avec logique métier
    public List<Facture> getFacturesNonPayees() {
        List<Facture> toutesFactures = factureRepository.findAll();

        return toutesFactures.stream()
                .filter(facture -> !estFacturePayee(facture))
                .collect(Collectors.toList());
    }

    // Logique de vérification de paiement
    private boolean estFacturePayee(Facture facture) {
        if (facture.getNote() == null || facture.getNote().trim().isEmpty()) {
            return false;
        }

        String note = facture.getNote().toUpperCase();
        return note.contains("PAYE") ||
                note.contains("PAYÉ") ||
                note.contains("PAID") ||
                note.contains("VIREMENT EFFECTUE") ||
                note.contains("CHEQUE ENCAISSE");
    }

    // Méthode pour les factures payées
    public List<Facture> findPayees() {
        return factureRepository.findByNoteNotContainingIgnoreCase("PAYE");
    }

    // Trouver les factures non payées
   // public List<Facture> findNonPayees() {
    //  return factureRepository.findByNoteNotContainingIgnoreCase("PAYE");
    //}

    // Trouver les factures par fournisseur
    public List<Facture> findByFournisseur(String idFournisseur) {
        return factureRepository.findByIdFournisseur(idFournisseur);
    }

    // Payer une facture
    public Facture payerFacture(String id, String notePaiement) {
        return factureRepository.findById(id)
                .map(facture -> {
                    facture.setNote(notePaiement);
                    // Ajouter un champ boolean payee dans le modèle si nécessaire
                    return factureRepository.save(facture);
                })
                .orElseThrow(() -> new RuntimeException("Facture non trouvée avec l'id: " + id));
    }

    // Créer une nouvelle facture
    public Facture save(Facture facture) {
        return factureRepository.save(facture);
    }

    // Mettre à jour une facture existante
    public Facture update(String id, Facture factureDetails) {
        return factureRepository.findById(id)
                .map(facture -> {
                    facture.setData(factureDetails.getData());
                    facture.setLibelle(factureDetails.getLibelle());
                    facture.setDebit(factureDetails.getDebit());
                    facture.setCredit(factureDetails.getCredit());
                    facture.setNote(factureDetails.getNote());
                    facture.setIdFournisseur(factureDetails.getIdFournisseur());
                    return factureRepository.save(facture);
                })
                .orElseThrow(() -> new RuntimeException("Facture non trouvée avec l'id: " + id));
    }

    // Supprimer une facture
    public void delete(String id) {
        factureRepository.deleteById(id);
    }
}
