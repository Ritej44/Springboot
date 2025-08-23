package com.example.WSM.Model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@Document(collection = "Fournisseur")
public class Fournisseur {
    @Id
    private String id;
    private String nom;
    private String service;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}