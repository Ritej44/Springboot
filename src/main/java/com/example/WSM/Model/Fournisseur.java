package com.example.WSM.Model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Getter
@Setter
@Document(collection = "Fournisseur")
public class Fournisseur {
    @Id
    private String id;
    @Field("nom")
    private String nom;
    private String Service;

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
        return Service;
    }

    public void setService(String service) {
        this.Service = service;
    }
}