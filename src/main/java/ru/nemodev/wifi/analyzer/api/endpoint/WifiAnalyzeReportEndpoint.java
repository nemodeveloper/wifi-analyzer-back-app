package ru.nemodev.wifi.analyzer.api.endpoint;

import io.swagger.annotations.Api;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nemodev.wifi.analyzer.entity.wifi_report.WifiAnalyzeReport;
import ru.nemodev.wifi.analyzer.service.wifi_report.WifiAnalyzeReportService;


@RestController
@RequestMapping(value = "/v1/analyze-report", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api("Wifi analyze report service information")
public class WifiAnalyzeReportEndpoint {

    private final WifiAnalyzeReportService wifiAnalyzeReportService;

    public WifiAnalyzeReportEndpoint(WifiAnalyzeReportService wifiAnalyzeReportService) {
        this.wifiAnalyzeReportService = wifiAnalyzeReportService;
    }

    @GetMapping
    public ResponseEntity getList(@RequestParam(value = "page", required = false) Integer page,
                                  @RequestParam(value = "size", required = false) Integer size) {
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 10;
        }

        return ResponseEntity.ok(wifiAnalyzeReportService.findBy(
                PageRequest.of(page, size,
                        Sort.by(Sort.Direction.DESC,"creationDate"))));
    }

    @GetMapping("/{id}")
    public ResponseEntity getDetail(@PathVariable("id") String id) {
        return ResponseEntity.of(wifiAnalyzeReportService.findById(id));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody WifiAnalyzeReport wifiAnalyzeReport) {
        return ResponseEntity.ok(wifiAnalyzeReportService.create(wifiAnalyzeReport));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        wifiAnalyzeReportService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
