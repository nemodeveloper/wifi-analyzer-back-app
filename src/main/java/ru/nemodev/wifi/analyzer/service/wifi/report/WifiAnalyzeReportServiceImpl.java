package ru.nemodev.wifi.analyzer.service.wifi.report;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.nemodev.wifi.analyzer.entity.location.Location;
import ru.nemodev.wifi.analyzer.entity.wifi.report.WifiAnalyzeReport;
import ru.nemodev.wifi.analyzer.repository.wifi.report.WifiAnalyzeReportRepository;
import ru.nemodev.wifi.analyzer.security.entity.user.User;
import ru.nemodev.wifi.analyzer.security.service.user.UserService;
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
    private final UserService userService;

    public WifiAnalyzeReportServiceImpl(WifiAnalyzeReportRepository wifiAnalyzeReportRepository,
                                        LocationService locationService, UserService userService) {
        this.wifiAnalyzeReportRepository = wifiAnalyzeReportRepository;
        this.locationService = locationService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public WifiAnalyzeReport create(String userLogin, WifiAnalyzeReport wifiAnalyzeReport) {

        User user = userService.findByLogin(userLogin)
                .orElseThrow(()-> new IllegalArgumentException(
                        String.format("Не удалось найти пользователя с userLogin=%s", userLogin)));

        wifiAnalyzeReport.setOwnerUser(user);

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
    public List<WifiAnalyzeReport> findBy(String locationId, Pageable pageable) {
        if (StringUtils.isEmpty(locationId)) {
            return wifiAnalyzeReportRepository.findAll(pageable).getContent();
        }
        return wifiAnalyzeReportRepository.findByLocationId(locationId, pageable).getContent();
    }
}
