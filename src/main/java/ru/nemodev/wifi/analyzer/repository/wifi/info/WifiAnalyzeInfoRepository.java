package ru.nemodev.wifi.analyzer.repository.wifi.info;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nemodev.wifi.analyzer.entity.wifi.info.WifiAnalyzeInfo;

/**
 * created by simanov-an
 */
@Repository
public interface WifiAnalyzeInfoRepository extends JpaRepository<WifiAnalyzeInfo, String> {
}
