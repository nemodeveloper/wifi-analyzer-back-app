package ru.nemodev.wifi.analyzer.config.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nemodev.wifi.analyzer.repository.wifi_report.WifiAnalyzeReportRepository;
import ru.nemodev.wifi.analyzer.service.wifi_report.WifiAnalyzeReportService;
import ru.nemodev.wifi.analyzer.service.wifi_report.WifiAnalyzeReportServiceImpl;


@Configuration
public class ServiceConfig
{
    private final WifiAnalyzeReportRepository wifiAnalyzeReportRepository;

    public ServiceConfig(WifiAnalyzeReportRepository wifiAnalyzeReportRepository)
    {
        this.wifiAnalyzeReportRepository = wifiAnalyzeReportRepository;
    }

    @Bean
    public WifiAnalyzeReportService wifiAnalyzeReportService() {
        return new WifiAnalyzeReportServiceImpl(wifiAnalyzeReportRepository);
    }

}
