package ru.nemodev.wifi.analyzer.api.endpoint;

import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api("Wifi analyzer service information")
public class WifiAnalyzerEndpoint {

    @GetMapping("/hello")
    public ResponseEntity<String> get()
    {
        return ResponseEntity.ok("hello");
    }

}
