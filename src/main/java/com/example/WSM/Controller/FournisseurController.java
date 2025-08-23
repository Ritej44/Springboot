package com.example.WSM.Controller;



import com.example.WSM.Model.Fournisseur;
import com.example.WSM.Service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fournisseurs")
@CrossOrigin(origins = "http://localhost:4200")

public class FournisseurController {

    @Autowired
    private FournisseurService fournisseurService;

    @GetMapping
    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurService.findAll();
    }

    @GetMapping("/{id}")
    public Fournisseur getFournisseurById(@PathVariable String id) {
        return fournisseurService.findById(id);
    }

    @PostMapping
    public Fournisseur createFournisseur(@RequestBody Fournisseur fournisseur) {
        return fournisseurService.save(fournisseur);
    }

    @PutMapping("/{id}")
    public Fournisseur updateFournisseur(@PathVariable String id, @RequestBody Fournisseur fournisseur) {
        return fournisseurService.update(id, fournisseur);
    }

    @DeleteMapping("/{id}")
    public void deleteFournisseur(@PathVariable String id) {
        fournisseurService.delete(id);
    }
}