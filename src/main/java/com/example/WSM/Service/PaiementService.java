package com.example.WSM.Service;


import com.example.WSM.Model.Paiement;
import com.example.WSM.Repository.PaiementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaiementService {

    @Autowired
    private PaiementRepository paiementRepository;


    public List<Paiement>findAll(){
        return paiementRepository.findAll();
    }

    public Paiement updatePaiement(String id, Paiement paiementDetails) {
        Paiement paiement = paiementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paiement non trouvé avec l'id : " + id));

        paiement.setSoldeDT(paiementDetails.getSoldeDT());
        paiement.setSoldeEURO(paiementDetails.getSoldeEURO());

        return paiementRepository.save(paiement);
    }

    public Paiement mettreAJourSolde(double montant, String devise) {
        // Récupérer le document existant (supposons qu'il n'y a qu'un seul document pour les soldes)
        Paiement paiement = paiementRepository.findAll().stream().findFirst()
                .orElse(new Paiement());

        // Mettre à jour le solde selon la devise
        if (devise.equalsIgnoreCase("DT")) {
            paiement.setSoldeDT(paiement.getSoldeDT() - montant);
        } else if (devise.equalsIgnoreCase("EURO")) {
            paiement.setSoldeEURO(paiement.getSoldeEURO() - montant);
        } else {
            throw new RuntimeException("Devise non valide : " + devise);
        }

        // Sauvegarder les modifications
        return paiementRepository.save(paiement);
    }
    public Paiement update (String id ,Paiement paiementDetails){
       return paiementRepository.findById(id)
                .map(paiement -> {
                    paiement.setSoldeDT(paiementDetails.getSoldeDT());
                    paiement.setSoldeEURO(paiementDetails.getSoldeEURO());
                    return paiementRepository.save(paiement);
                }).orElseThrow(()-> new RuntimeException("paiement non trouvé"+id));
    }
}
