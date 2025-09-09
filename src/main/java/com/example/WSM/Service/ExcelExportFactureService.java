package com.example.WSM.Service;
import com.example.WSM.Model.Facture;
import com.example.WSM.Model.Fournisseur;
import com.example.WSM.Model.Paiement;
import com.example.WSM.Repository.FournisseurRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
@Service
public class ExcelExportFactureService {
    @Autowired
    private FournisseurService fournisseurService;

    public ByteArrayInputStream exportFactures(List<Facture> factures, Double soldeDT, Double soldeEURO) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Factures");

            // --- Header Section: Bank Balances ---
            Row balanceHeaderRow = sheet.createRow(0);
            balanceHeaderRow.createCell(0).setCellValue("Solde COMPTE DT:");
            balanceHeaderRow.createCell(1).setCellValue(soldeDT);
            balanceHeaderRow.createCell(3).setCellValue("Solde COMPTE EURO:");
            balanceHeaderRow.createCell(4).setCellValue(soldeEURO);

            // --- Table Header ---
            Row tableHeaderRow = sheet.createRow(2);
            tableHeaderRow.createCell(0).setCellValue("Date");
            tableHeaderRow.createCell(1).setCellValue("Libellé");
            tableHeaderRow.createCell(2).setCellValue("Débit");
            tableHeaderRow.createCell(3).setCellValue("Crédit");
            tableHeaderRow.createCell(4).setCellValue("Fournisseur");
            tableHeaderRow.createCell(5).setCellValue("NOTE");

            // --- Table Data ---
            int rowIdx = 3;
            for (Facture facture : factures) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(facture.getDate() != null ? facture.getDate().toString() : "");
                row.createCell(1).setCellValue(facture.getLibelle() != null ? facture.getLibelle() : "");
                row.createCell(2).setCellValue(facture.getDebit() != null ? facture.getDebit().toString() : "");
                row.createCell(3).setCellValue(facture.getCredit() != null ? facture.getCredit().toString() : "");

                String nomFournisseur = "";
                if (facture.getIdFournisseur() != null) {
                    Fournisseur fournisseur = fournisseurService.findnomById(facture.getIdFournisseur());
                    if (fournisseur != null) {
                        nomFournisseur = fournisseur.getNom();
                    }
                }
                row.createCell(4).setCellValue(nomFournisseur);
                row.createCell(5).setCellValue(facture.getNote() != null ? facture.getNote() : "");
            }

            // Auto-size columns for better readability
            for (int i = 0; i < 6; i++) {
                sheet.autoSizeColumn(i);
            }

            // Write to output stream
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
