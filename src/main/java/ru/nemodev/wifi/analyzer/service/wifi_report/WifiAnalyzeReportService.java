package ru.nemodev.wifi.analyzer.service.wifi_report;

import org.springframework.data.domain.Pageable;
import ru.nemodev.wifi.analyzer.entity.wifi_report.WifiAnalyzeReport;

import java.util.List;
import java.util.Optional;

public interface WifiAnalyzeReportService {

    WifiAnalyzeReport create(WifiAnalyzeReport wifiAnalyzeReport);
    Optional<WifiAnalyzeReport> findById(String id);
    void deleteById(String id);
    List<WifiAnalyzeReport> findBy(Pageable pageable);

}
