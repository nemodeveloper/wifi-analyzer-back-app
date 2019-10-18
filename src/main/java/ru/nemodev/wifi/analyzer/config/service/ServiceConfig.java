package ru.nemodev.wifi.analyzer.config.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nemodev.wifi.analyzer.repository.wifi.info.WifiAnalyzeInfoRepository;
import ru.nemodev.wifi.analyzer.repository.wifi.report.WifiAnalyzeReportRepository;
import ru.nemodev.wifi.analyzer.service.wifi.info.WifiAnalyzeInfoService;
import ru.nemodev.wifi.analyzer.service.wifi.info.WifiAnalyzeInfoServiceImpl;
import ru.nemodev.wifi.analyzer.service.wifi.report.WifiAnalyzeReportService;
import ru.nemodev.wifi.analyzer.service.wifi.report.WifiAnalyzeReportServiceImpl;


@Configuration
public class ServiceConfig
{
    private final WifiAnalyzeInfoRepository wifiAnalyzeInfoRepository;
    private final WifiAnalyzeReportRepository wifiAnalyzeReportRepository;

    public ServiceConfig(WifiAnalyzeInfoRepository wifiAnalyzeInfoRepository,
                         WifiAnalyzeReportRepository wifiAnalyzeReportRepository)
    {
        this.wifiAnalyzeInfoRepository = wifiAnalyzeInfoRepository;
        this.wifiAnalyzeReportRepository = wifiAnalyzeReportRepository;
    }

    @Bean
    public WifiAnalyzeInfoService wifiAnalyzeInfoService() {
        return new WifiAnalyzeInfoServiceImpl(wifiAnalyzeInfoRepository);
    }

    @Bean
    public WifiAnalyzeReportService wifiAnalyzeReportService() {
        return new WifiAnalyzeReportServiceImpl(wifiAnalyzeReportRepository);
    }

}
