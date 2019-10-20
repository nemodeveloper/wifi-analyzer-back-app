package ru.nemodev.wifi.analyzer.service.wifi.report;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.nemodev.wifi.analyzer.entity.location.Location;
import ru.nemodev.wifi.analyzer.entity.wifi.report.WifiAnalyzeReport;
import ru.nemodev.wifi.analyzer.repository.wifi.report.WifiAnalyzeReportRepository;
import ru.nemodev.wifi.analyzer.service.location.LocationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * created by simanov-an
 */
public class WifiAnalyzeReportServiceImpl implements WifiAnalyzeReportService {

    private final WifiAnalyzeReportRepository wifiAnalyzeReportRepository;
    private final LocationService locationService;

    public WifiAnalyzeReportServiceImpl(WifiAnalyzeReportRepository wifiAnalyzeReportRepository, LocationService locationService) {
        this.wifiAnalyzeReportRepository = wifiAnalyzeReportRepository;
        this.locationService = locationService;
    }

    @Override
    @Transactional
    public WifiAnalyzeReport create(WifiAnalyzeReport wifiAnalyzeReport) {

        Location location = locationService.findById(wifiAnalyzeReport.getLocation().getId())
                .orElseThrow(()-> new IllegalArgumentException(
                        String.format("Не удалось найти локацию с id=%s", wifiAnalyzeReport.getLocation().getId())));

        wifiAnalyzeReport.setLocation(location);

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
