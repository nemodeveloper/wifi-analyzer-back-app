package ru.nemodev.wifi.analyzer.api.endpoint;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.nemodev.wifi.analyzer.api.dto.location.LocationDto;
import ru.nemodev.wifi.analyzer.entity.location.Location;
import ru.nemodev.wifi.analyzer.service.location.LocationService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v1/location", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api("Locations information")
// TODO добавить Processor запросов
public class LocationEndpoint {

    private final LocationService locationService;

    public LocationEndpoint(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    @ApiOperation(value = "Get all")
    @PreAuthorize("hasAnyRole('ADMIN', 'ANALYZER')")
    public ResponseEntity<List<LocationDto>> getList() {
        return ResponseEntity.ok(locationService.getAll().stream()
                .map(LocationDto::fromEntity)
                .collect(Collectors.toList()));
    }

    @PostMapping
    @ApiOperation(value = "Create location")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LocationDto> create(@RequestBody LocationDto locationDto) {
        return ResponseEntity.ok(LocationDto.fromEntity(locationService.create(locationDto.toEntity())));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update by id")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity updateById(@PathVariable("id") String id, @RequestBody LocationDto locationDto) {
        Location location = locationDto.toEntity();
        location.setId(id);
        return ResponseEntity.ok(locationService.save(location));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete by id")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteById(@PathVariable("id") String id) {
        locationService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
