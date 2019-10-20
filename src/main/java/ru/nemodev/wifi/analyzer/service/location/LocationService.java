package ru.nemodev.wifi.analyzer.service.location;

import ru.nemodev.wifi.analyzer.entity.location.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {

    Location create(Location location);
    Location save(Location location);
    Optional<Location> findById(String id);
    void deleteById(String id);
    List<Location> getAll();

}
