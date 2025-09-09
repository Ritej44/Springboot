package com.example.WSM.Model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Getter
@Setter
@Document(collection = "Facture")
public class Facture {


    @Id
    private String id;
    @Field("DATA")
    private String data;
    @Field("LIBELLE")
    private String libelle;
    @Field("débit")
    private Double debit;
    @Field("crédit")
    private Double credit;
    @Field ("NOTE" )
    private String note;
    @Field("Id_fournisseur")
    private String idFournisseur;
    private String modeDePayement;

    private transient String fournisseurNom;

    public String getStatut() {
        if (note != null && note.toUpperCase().contains("PAYE")) {
            return "Payée";
        }
        return "Non payée";
    }

    // Méthode pour vérifier si la facture est payée
    public boolean isPayee() {
        if (note == null || note.trim().isEmpty()) {
            return false;
        }


        String noteUpper = note.toUpperCase();

        // Vérifie les motifs indiquant un paiement
        return noteUpper.contains("PAYE") ||
                noteUpper.contains("PAYÉ") ||
                noteUpper.contains("PAID") ||
                noteUpper.contains("REGLE") ||
                noteUpper.contains("SETTLE") ||
                noteUpper.contains("VIREMENT EFFECTUE") ||
                noteUpper.contains("CHEQUE ENCAISSE");
    }

    // Méthode pour extraire le mode de paiement
    public String getModePaiement() {
        if (note == null) return null;

        String noteUpper = note.toUpperCase();

        if (noteUpper.contains("VIREMENT")) return "VIREMENT";
        if (noteUpper.contains("CHEQUE")) return "CHEQUE";
        if (noteUpper.contains("CARTE")) return "CARTE";
        if (noteUpper.contains("ESPECE") || noteUpper.contains("CASH")) return "ESPECE";
        if (noteUpper.contains("CAISSE")) return "CAISSE";
        if (noteUpper.contains("COMPENSATION")) return "COMPENSATION";

        return "AUTRE";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public Object getDate() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(String idFournisseur) {
        this.idFournisseur = idFournisseur;
    }



    public String getModeDePayement() {
        return modeDePayement;
    }

    public void setModeDePayement(String modeDePayement) {
        this.modeDePayement = modeDePayement;
    }


}
