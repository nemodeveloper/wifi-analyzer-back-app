package ru.nemodev.wifi.analyzer.repository.location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nemodev.wifi.analyzer.entity.location.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, String> {
}
