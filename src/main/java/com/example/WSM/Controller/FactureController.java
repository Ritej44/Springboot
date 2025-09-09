package com.example.WSM.Controller;


import com.example.WSM.Model.Facture;
import com.example.WSM.Model.Paiement;
import com.example.WSM.Repository.PaiementRepository;
import com.example.WSM.Service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/factures")
@CrossOrigin(origins = "http://localhost:4200 ,https://angular-gray-mu.vercel.app")
public class FactureController {

    @Autowired
    private FactureService factureService;

    @Autowired
    private PaiementRepository paiementRepository;

    @GetMapping("/getAll")
    public List<Facture> getAllFactures() {
        return factureService.findAll();
    }

    @GetMapping("/paye")
    public List<Facture> getFacturesWithPaye() {
        return factureService.getFacturesWithPaye();
    }
@GetMapping("/no-paye")
    public List<Facture> getFacturesNOTPaye() {
        return factureService.getFacturesNOTPaye();
    }

    // Endpoint pour rechercher par mot-clé dans la note
    @GetMapping("/search")
    public List<Facture> searchFacturesByNote(@RequestParam String keyword) {
        return factureService.getFacturesByNoteContent(keyword);
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
    public Facture payerFacture(@PathVariable String id, @RequestBody String notePaiement,@RequestBody String devise) {
        return factureService.payerFacture(id, notePaiement,devise);
    }

    @PostMapping("/{id}/payer")
    public ResponseEntity<Facture> payerFacture(
            @PathVariable String id,
            @RequestBody Map<String, String> payload) {
        String notePaiement = payload.get("notePaiement");
        String devise = payload.get("devise");
        Facture facturePayee = factureService.payerFacture(id, notePaiement, devise);
        return ResponseEntity.ok(facturePayee);
    }

    @PostMapping  (path = "/create")
    public Facture createFacture(@RequestBody Facture facture) {
        return factureService.save(facture);
    }

    @PutMapping("update/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Facture updateFacture(@PathVariable String id, @RequestBody Facture facture) {
        return factureService.update(id, facture);
    }

    @DeleteMapping("/{id}")
    public void deleteFacture(@PathVariable String id) {
        factureService.delete(id);
    }
}
