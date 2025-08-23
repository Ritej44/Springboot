package com.example.WSM.Service;




import com.example.WSM.Model.Fournisseur;
import com.example.WSM.Repository.FournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FournisseurService {

    private final FournisseurRepository fournisseurRepository;

    @Autowired
    public FournisseurService(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    /**
     * Récupère tous les fournisseurs
     * @return Liste de tous les fournisseurs
     */
    public List<Fournisseur> findAll() {
        return fournisseurRepository.findAll();
    }

    /**
     * Trouve un fournisseur par son ID
     * @param id ID du fournisseur
     * @return Fournisseur correspondant
     * @throws RuntimeException si le fournisseur n'est pas trouvé
     */
    public Fournisseur findById(String id) {
        return fournisseurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé avec l'id: " + id));
    }

    /**
     * Crée un nouveau fournisseur
     * @param fournisseur Données du fournisseur à créer
     * @return Fournisseur créé
     */
    public Fournisseur save(Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }

    /**
     * Met à jour un fournisseur existant
     * @param id ID du fournisseur à mettre à jour
     * @param fournisseurDetails Nouvelles données du fournisseur
     * @return Fournisseur mis à jour
     * @throws RuntimeException si le fournisseur n'est pas trouvé
     */
    public Fournisseur update(String id, Fournisseur fournisseurDetails) {
        return fournisseurRepository.findById(id)
                .map(existingFournisseur -> {
                    existingFournisseur.setNom(fournisseurDetails.getNom());
                    existingFournisseur.setService(fournisseurDetails.getService());
                    return fournisseurRepository.save(existingFournisseur);
                })
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé avec l'id: " + id));
    }

    /**
     * Supprime un fournisseur
     * @param id ID du fournisseur à supprimer
     */
    public void delete(String id) {
        fournisseurRepository.deleteById(id);
    }
}