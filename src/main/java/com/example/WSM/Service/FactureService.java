package com.example.WSM.Service;

import com.example.WSM.Model.Facture;
import com.example.WSM.Model.Paiement;
import com.example.WSM.Repository.FactureRepository;
import com.example.WSM.Repository.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FactureService {

    @Autowired
    private FactureRepository factureRepository;

    private Facture facture;

    private Paiement paiement;

    @Autowired
    private PaiementRepository paiementRepository;

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


    // Récupérer toutes les factures avec "PAYE" dans la note
    public List<Facture> getFacturesWithPaye() {
        return factureRepository.findFacturesWithPaye();
    }
    public List<Facture> getFacturesNOTPaye() {
        return factureRepository.findFacturesNOTPaye();
    }

    // Récupérer les factures par mot-clé dans la note
    public List<Facture> getFacturesByNoteContent(String keyword) {
        return factureRepository.findByNoteContaining(keyword);
    }


    // Trouver les factures non payées
   // public List<Facture> findNonPayees() {
    //  return factureRepository.findByNoteNotContainingIgnoreCase("PAYE");
    //}

    // Trouver les factures par fournisseur
    public List<Facture> findByFournisseur(String idFournisseur) {
        return factureRepository.findByIdFournisseur(idFournisseur);
    }

    public Facture payerFacture(String id, String notePaiement, String devise) {
        return factureRepository.findById(id)
                .map(facture -> {
                    facture.setNote(notePaiement);
                    facture.setCredit(facture.getDebit());

                    // Récupérer le document Paiement depuis la base de données
                    Paiement paiement = paiementRepository.findAll().stream().findFirst()
                            .orElseThrow(() -> new RuntimeException("Aucun document Paiement trouvé"));

                    // Mettre à jour le solde selon la devise
                    if (devise.equalsIgnoreCase("DT")) {
                        paiement.setSoldeDT(paiement.getSoldeDT() - facture.getDebit());
                    } else if (devise.equalsIgnoreCase("EURO")) {
                        paiement.setSoldeEURO(paiement.getSoldeEURO() - facture.getDebit());
                    } else {
                        throw new RuntimeException("Devise non valide : " + devise);
                    }

                    // Sauvegarder les modifications du paiement
                    paiementRepository.save(paiement);

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
