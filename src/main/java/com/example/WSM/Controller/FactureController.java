package com.example.WSM.Controller;


import com.example.WSM.Model.Facture;
import com.example.WSM.Service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/factures")
@CrossOrigin(origins = "http://localhost:4200")
public class FactureController {

    @Autowired
    private FactureService factureService;

    @GetMapping("/getAll")
    public List<Facture> getAllFactures() {
        return factureService.findAll();
    }

    @GetMapping("/non-payees")
    public List<Facture> getFacturesNonPayees() {
        return factureService.findNonPayees();
    }

    // Endpoint pour les factures PAYÉES
    @GetMapping("/payees")
    public List<Facture> getFacturesPayees() {
        return factureService.findPayees();
    }

    // Endpoint avec filtre statut
    @GetMapping("/statut/{statut}")
    public List<Facture> getFacturesByStatut(@PathVariable String statut) {
        if ("paye".equalsIgnoreCase(statut)) {
            return factureService.findPayees();
        } else if ("non-paye".equalsIgnoreCase(statut)) {
            return factureService.findNonPayees();
        } else {
            return factureService.findAll();
        }
    }

    @GetMapping("/fournisseur/{idFournisseur}")
    public List<Facture> getFacturesByFournisseur(@PathVariable String idFournisseur) {
        return factureService.findByFournisseur(idFournisseur);
    }

    @PostMapping("/paiement/{id}")
    public Facture payerFacture(@PathVariable String id, @RequestBody String notePaiement) {
        return factureService.payerFacture(id, notePaiement);
    }

    @PostMapping
    public Facture createFacture(@RequestBody Facture facture) {
        return factureService.save(facture);
    }

    @PutMapping("/{id}")
    public Facture updateFacture(@PathVariable String id, @RequestBody Facture facture) {
        return factureService.update(id, facture);
    }

    @DeleteMapping("/{id}")
    public void deleteFacture(@PathVariable String id) {
        factureService.delete(id);
    }
}
