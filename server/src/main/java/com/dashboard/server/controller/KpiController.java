package com.dashboard.server.controller;

import java.util.List;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dashboard.server.dto.KPI.KpiDto;
import com.dashboard.server.service.KpiService;

@RestController
@RequestMapping("/kpi")
public class KpiController {
    // private final Logger LOGGER = LoggerFactory.getLogger(KpiController.class);

    @Autowired
    KpiService KpiService;

    @GetMapping(value = "/kpis")
    public ResponseEntity<List<KpiDto>> getKPIs() {
        List<KpiDto> kpis = KpiService.getAllKPIs();
        return new ResponseEntity<>(kpis, HttpStatus.OK);
    }
}
