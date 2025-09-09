package com.example.WSM.Model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Getter
@Setter
@Document(collection ="Solde")
        public class Paiement {
    @Id
    private String id;

    private double SoldeDT ;
    private double SoldeEURO;


    public double getSoldeEURO() {
        return SoldeEURO;
    }

    public void setSoldeEURO(double soldeEURO) {
        SoldeEURO = soldeEURO;
    }

    public double getSoldeDT() {
        return SoldeDT;
    }

    public void setSoldeDT(double soldeDT) {
        SoldeDT = soldeDT;
    }

    public void updateSoldes(Double nouveauSoldeDT, Double nouveauSoldeEURO) {
        if (nouveauSoldeDT != null) {
            this.SoldeDT = nouveauSoldeDT;
        }
        if (nouveauSoldeEURO != null) {
            this.SoldeEURO = nouveauSoldeEURO;
        }
    }
}
