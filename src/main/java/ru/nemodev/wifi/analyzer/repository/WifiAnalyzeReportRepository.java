package ru.nemodev.wifi.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nemodev.wifi.analyzer.entity.WifiAnalyzeReport;

/**
 * created by simanov-an
 */
@Repository
public interface WifiAnalyzeReportRepository extends JpaRepository<WifiAnalyzeReport, String> {
}
