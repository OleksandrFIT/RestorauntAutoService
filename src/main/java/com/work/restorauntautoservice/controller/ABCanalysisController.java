package com.work.restorauntautoservice.controller;


import com.work.restorauntautoservice.analysisAl.ABCanalysisService;
import com.work.restorauntautoservice.model.Product;
import kotlin.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class ABCanalysisController {
    @Autowired
    private ABCanalysisService abCanalysisService;

    @GetMapping("/abc-analysis/{percentA}/{percentB}") //0.8, 0.95
    @ResponseBody
    public ResponseEntity<Triple<List<Product>, List<Product>, List<Product>>> abcAnalysis(@PathVariable double percentA, @PathVariable double percentB) {
        Triple<List<Product>, List<Product>, List<Product>> result = abCanalysisService.abcAnalysis(percentA, percentB);
        return ResponseEntity.ok(result);
    }
}
