package com.example.WSM.Controller;


import com.example.WSM.Model.Facture;
import com.example.WSM.Model.Paiement;
import com.example.WSM.Service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/paiement")
@CrossOrigin(origins = "http://localhost:4200 , https://angular-gray-mu.vercel.app")
public class PaiementController {

    @Autowired
    private PaiementService paiementService;


    @PutMapping("/{id}")
    public Paiement updatePaiement(@PathVariable String id, @RequestBody Paiement paiementDetails) {
        return paiementService.updatePaiement(id, paiementDetails);
    }

    @GetMapping("/getAll")
    public List<Paiement> getAllSoldes() {
        return paiementService.findAll();
    }



}
