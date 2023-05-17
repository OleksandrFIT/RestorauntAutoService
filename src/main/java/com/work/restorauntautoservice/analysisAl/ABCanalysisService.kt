package com.work.restorauntautoservice.analysisAl

import com.work.restorauntautoservice.model.Product

interface ABCanalysisService {
    fun abcAnalysis(
        percentA: Double = 0.8,
        percentB: Double = 0.95
    ): Triple<List<Product>, List<Product>, List<Product>>
}