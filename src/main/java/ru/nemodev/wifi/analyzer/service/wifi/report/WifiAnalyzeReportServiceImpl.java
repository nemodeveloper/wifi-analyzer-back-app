package ru.nemodev.wifi.analyzer.service.wifi.report;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.nemodev.wifi.analyzer.entity.wifi.report.WifiAnalyzeReport;
import ru.nemodev.wifi.analyzer.repository.wifi.report.WifiAnalyzeReportRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * created by simanov-an
 */
public class WifiAnalyzeReportServiceImpl implements WifiAnalyzeReportService {

    private final WifiAnalyzeReportRepository wifiAnalyzeReportRepository;

    public WifiAnalyzeReportServiceImpl(WifiAnalyzeReportRepository wifiAnalyzeReportRepository) {
        this.wifiAnalyzeReportRepository = wifiAnalyzeReportRepository;
    }

    @Override
    @Transactional
    public WifiAnalyzeReport create(WifiAnalyzeReport wifiAnalyzeReport) {
        final LocalDateTime nowDate = LocalDateTime.now();
        wifiAnalyzeReport.setCreationDate(nowDate);
        wifiAnalyzeReport.getWifiAnalyzeInfoList()
                .forEach(wifiAnalyzeInfo -> {
                    wifiAnalyzeInfo.setCreationDate(nowDate);
                    wifiAnalyzeInfo.setWifiAnalyzeReport(wifiAnalyzeReport);
                });

        return wifiAnalyzeReportRepository.save(wifiAnalyzeReport);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<WifiAnalyzeReport> findById(String id) {
        return wifiAnalyzeReportRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        if (wifiAnalyzeReportRepository.existsById(id)) {
            wifiAnalyzeReportRepository.deleteById(id);
        }
        else {
            throw new IllegalArgumentException(String.format("Не удалось найти wifi-report с ID=%s!", id));
        }
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<WifiAnalyzeReport> findBy(Pageable pageable) {
        return wifiAnalyzeReportRepository.findAll(pageable).getContent();
    }
}
