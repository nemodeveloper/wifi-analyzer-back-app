package ru.nemodev.wifi.analyzer.api.processor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import ru.nemodev.wifi.analyzer.api.dto.wifi.report.WifiAnalyzeReportDto;
import ru.nemodev.wifi.analyzer.entity.wifi.report.WifiAnalyzeReport;
import ru.nemodev.wifi.analyzer.service.wifi.report.WifiAnalyzeReportService;

import java.util.List;
import java.util.Optional;

/**
 * created by simanov-an
 */
public class WifiAnalyzeReportProcessor {

    private final WifiAnalyzeReportService wifiAnalyzeReportService;

    public WifiAnalyzeReportProcessor(WifiAnalyzeReportService wifiAnalyzeReportService) {
        this.wifiAnalyzeReportService = wifiAnalyzeReportService;
    }

    public List<WifiAnalyzeReportDto> findBy(Integer page, Integer size) {
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 10;
        }

        List<WifiAnalyzeReport> wifiAnalyzeReports =
                wifiAnalyzeReportService.findBy(PageRequest.of(page, size,
                        Sort.by(Sort.Direction.DESC, "creationDate")));

        return WifiAnalyzeReportDto.fromList(wifiAnalyzeReports);
    }

    public Optional<WifiAnalyzeReportDto> findById(String id) {
        Optional<WifiAnalyzeReport> reportOptional = wifiAnalyzeReportService.findById(id);
        if (reportOptional.isEmpty())
            return Optional.empty();

        return Optional.of(WifiAnalyzeReportDto.fromEntity(reportOptional.get()));
    }

    public WifiAnalyzeReportDto create(WifiAnalyzeReportDto wifiAnalyzeReportDto) {
        return WifiAnalyzeReportDto.fromEntity(
                wifiAnalyzeReportService.create(wifiAnalyzeReportDto.toEntity()));
    }

    public void deleteById(String id) {
        wifiAnalyzeReportService.deleteById(id);
    }
}
