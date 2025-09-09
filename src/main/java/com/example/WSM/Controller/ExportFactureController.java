package com.example.WSM.Controller;

import com.example.WSM.Model.Facture;
import com.example.WSM.Model.Paiement;
import com.example.WSM.Repository.FactureRepository;
import com.example.WSM.Repository.PaiementRepository;
import com.example.WSM.Service.ExcelExportFactureService;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;@RestController
@RequestMapping("/api/factures")
public class ExportFactureController {

    @Autowired
    private ExcelExportFactureService excelExportFactureService;

    @Autowired
    private FactureRepository factureRepository;

    @Autowired
    private PaiementRepository paiementRepository;

    @GetMapping("/export/excel")
    public ResponseEntity<InputStreamResource> exportToExcel() throws IOException {
        List<Facture> factures = factureRepository.findAll();

        List<Paiement> paiements = paiementRepository.findAll();
        Paiement paiement = !paiements.isEmpty() ? paiements.get(0) : null;

        Double soldeDT = (paiement != null) ? paiement.getSoldeDT() : 0.0;
        Double soldeEURO = (paiement != null) ? paiement.getSoldeEURO() : 0.0;

        ByteArrayInputStream in = excelExportFactureService.exportFactures(factures, soldeDT, soldeEURO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=factures.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(in));
    }
}
