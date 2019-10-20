package ru.nemodev.wifi.analyzer.config.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nemodev.wifi.analyzer.repository.location.LocationRepository;
import ru.nemodev.wifi.analyzer.repository.wifi.info.WifiAnalyzeInfoRepository;
import ru.nemodev.wifi.analyzer.repository.wifi.report.WifiAnalyzeReportRepository;
import ru.nemodev.wifi.analyzer.service.location.LocationService;
import ru.nemodev.wifi.analyzer.service.location.LocationServiceImpl;
import ru.nemodev.wifi.analyzer.service.wifi.info.WifiAnalyzeInfoService;
import ru.nemodev.wifi.analyzer.service.wifi.info.WifiAnalyzeInfoServiceImpl;
import ru.nemodev.wifi.analyzer.service.wifi.report.WifiAnalyzeReportService;
import ru.nemodev.wifi.analyzer.service.wifi.report.WifiAnalyzeReportServiceImpl;


@Configuration
public class ServiceConfig
{
    private final WifiAnalyzeInfoRepository wifiAnalyzeInfoRepository;
    private final WifiAnalyzeReportRepository wifiAnalyzeReportRepository;
    private final LocationRepository locationRepository;

    public ServiceConfig(WifiAnalyzeInfoRepository wifiAnalyzeInfoRepository,
                         WifiAnalyzeReportRepository wifiAnalyzeReportRepository,
                         LocationRepository locationRepository)
    {
        this.wifiAnalyzeInfoRepository = wifiAnalyzeInfoRepository;
        this.wifiAnalyzeReportRepository = wifiAnalyzeReportRepository;
        this.locationRepository = locationRepository;
    }

    @Bean
    public WifiAnalyzeInfoService wifiAnalyzeInfoService() {
        return new WifiAnalyzeInfoServiceImpl(wifiAnalyzeInfoRepository);
    }

    @Bean
    public WifiAnalyzeReportService wifiAnalyzeReportService() {
        return new WifiAnalyzeReportServiceImpl(wifiAnalyzeReportRepository, locationService());
    }

    @Bean
    public LocationService locationService() {
        return new LocationServiceImpl(locationRepository);
    }

}
