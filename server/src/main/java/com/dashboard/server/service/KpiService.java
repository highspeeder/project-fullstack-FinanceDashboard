package com.dashboard.server.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dashboard.server.dto.KPI.DayDataDto;
import com.dashboard.server.dto.KPI.KpiDto;
import com.dashboard.server.dto.KPI.MonthDataDto;
import com.dashboard.server.entity.KPI.DayData;
import com.dashboard.server.entity.KPI.Kpi;
import com.dashboard.server.entity.KPI.MonthData;
import com.dashboard.server.repository.KpiRepository;

@Service
public class KpiService {
    private final Logger LOGGER = LoggerFactory.getLogger(KpiService.class);

    @Autowired
    private KpiRepository kpiRepository;

    public List<KpiDto> getAllKPIs() {
        long startTime = System.currentTimeMillis();
        LOGGER.info("[KpiService] getAllKPIs실행 시작");

        var kpiEntity = kpiRepository.findAll();

        List<KpiDto> newKpiDtos = new ArrayList<KpiDto>();
        for (Kpi kpi : kpiEntity) {
            KpiDto newKpiDto = KpiDto.builder()
                    .id(kpi.getId())
                    .totalProfit(kpi.getTotalProfit())
                    .totalRevenue(kpi.getTotalRevenue())
                    .totalExpenses(kpi.getTotalExpenses())
                    .expensesByCategory(kpi.getExpensesByCategory())
                    .build();

            List<DayDataDto> newDayDatas = new ArrayList<>();
            for (DayData daydata : kpi.getDayData()) {
                newDayDatas.add(new DayDataDto(
                        daydata.getId(),
                        daydata.getDate(),
                        daydata.getRevenue(),
                        daydata.getExpenses()));
            }
            newKpiDto.setDayData(newDayDatas);

            List<MonthDataDto> newMonthDatas = new ArrayList<>();
            for (MonthData monthData : kpi.getMonthData()) {
                newMonthDatas.add(new MonthDataDto(
                        monthData.getId(),
                        monthData.getMonth(),
                        monthData.getRevenue(),
                        monthData.getExpenses(),
                        monthData.getOperationalExpenses(),
                        monthData.getNonOperationalExpenses()));
            }
            newKpiDto.setMonthData(newMonthDatas);

            newKpiDtos.add(newKpiDto);
        }

        long stopTime = System.currentTimeMillis();
        LOGGER.info("[KpiService] getAllKPIs실행 종료 - 총시간: " + (stopTime - startTime));

        return newKpiDtos;
    }
}
