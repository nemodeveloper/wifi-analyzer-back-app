package ru.nemodev.wifi.analyzer.service.location;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.nemodev.wifi.analyzer.entity.location.Location;
import ru.nemodev.wifi.analyzer.repository.location.LocationRepository;

import java.util.List;
import java.util.Optional;

public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    @Transactional
    public Location create(Location location) {
        return locationRepository.saveAndFlush(location);
    }

    @Override
    @Transactional
    public Location save(Location location) {
        if (StringUtils.isEmpty(location.getId())) {
            throw new IllegalArgumentException("Не задан id локации для обновления!");
        }

        return locationRepository.saveAndFlush(location);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Optional<Location> findById(String id) {
        return locationRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException(String.format("Не удалось найти локацию с id=%s", id)));

        locationRepository.delete(location);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Location> getAll() {
        return locationRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }
}
