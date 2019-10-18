package ru.nemodev.wifi.analyzer.service.wifi.info;

import org.springframework.data.domain.Pageable;
import ru.nemodev.wifi.analyzer.entity.wifi.info.WifiAnalyzeInfo;

import java.util.List;
import java.util.Optional;

public interface WifiAnalyzeInfoService {

    Optional<WifiAnalyzeInfo> findById(String id);
    List<WifiAnalyzeInfo> findBy(Pageable pageable);

}
