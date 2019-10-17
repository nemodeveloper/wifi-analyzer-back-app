package ru.nemodev.wifi.analyzer.service.wifi_report;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.nemodev.wifi.analyzer.entity.wifi_report.WifiAnalyzeReport;
import ru.nemodev.wifi.analyzer.repository.wifi_report.WifiAnalyzeReportRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public class WifiAnalyzeReportServiceImpl implements WifiAnalyzeReportService {

    private final WifiAnalyzeReportRepository wifiAnalyzeReportRepository;

    public WifiAnalyzeReportServiceImpl(WifiAnalyzeReportRepository wifiAnalyzeReportRepository) {
        this.wifiAnalyzeReportRepository = wifiAnalyzeReportRepository;
    }

    @Override
    @Transactional
    public WifiAnalyzeReport create(WifiAnalyzeReport wifiAnalyzeReport) {
        wifiAnalyzeReport.setCreationDate(LocalDateTime.now());
        return wifiAnalyzeReportRepository.saveAndFlush(wifiAnalyzeReport);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<WifiAnalyzeReport> findById(String id) {
        return wifiAnalyzeReportRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        wifiAnalyzeReportRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException(String.format("Не удалось найти wifi-отчет с ID=%s!", id)));
        wifiAnalyzeReportRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<WifiAnalyzeReport> findBy(Pageable pageable) {
        return wifiAnalyzeReportRepository.findAll(pageable).getContent();
    }
}
