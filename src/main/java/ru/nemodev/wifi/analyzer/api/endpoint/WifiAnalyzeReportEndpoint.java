package ru.nemodev.wifi.analyzer.api.endpoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nemodev.wifi.analyzer.api.dto.wifi.report.WifiAnalyzeReportDto;
import ru.nemodev.wifi.analyzer.api.processor.WifiAnalyzeReportProcessor;

import java.util.List;


@RestController
@RequestMapping(value = "/v1/report", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api("Wifi analyze report service information")
public class WifiAnalyzeReportEndpoint {

    private final WifiAnalyzeReportProcessor wifiAnalyzeReportProcessor;

    public WifiAnalyzeReportEndpoint(WifiAnalyzeReportProcessor wifiAnalyzeReportProcessor) {
        this.wifiAnalyzeReportProcessor = wifiAnalyzeReportProcessor;
    }

    @GetMapping
    @ApiOperation(value = "Find by params")
    public ResponseEntity<List<WifiAnalyzeReportDto>> findBy(@RequestParam(value = "page", required = false) Integer page,
                                                              @RequestParam(value = "size", required = false) Integer size) {
        return ResponseEntity.ok(wifiAnalyzeReportProcessor.findBy(page, size));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find report by id")
    public ResponseEntity<WifiAnalyzeReportDto> findById(@PathVariable("id") String id) {
        return ResponseEntity.of(wifiAnalyzeReportProcessor.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "Create report")
    public ResponseEntity create(@RequestBody WifiAnalyzeReportDto wifiAnalyzeReportDto) {
        return ResponseEntity.ok(wifiAnalyzeReportProcessor.create(wifiAnalyzeReportDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete by id")
    public ResponseEntity deleteById(@PathVariable("id") String id) {
        wifiAnalyzeReportProcessor.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
