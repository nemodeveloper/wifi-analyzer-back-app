package ru.nemodev.wifi.analyzer.entity.wifi.info;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nemodev.wifi.analyzer.core.entity.BaseEntity;
import ru.nemodev.wifi.analyzer.entity.wifi.report.WifiAnalyzeReport;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * created by simanov-an
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "WIFI_ANALYZES_INFO")
public class WifiAnalyzeInfo extends BaseEntity {

    @Column(name = "CREATION_DATE", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "SSID", nullable = false)
    private String SSID;

    @Column(name = "BSSID", nullable = false)
    private String BSSID;

    @Column(name = "RSSI", nullable = false)
    private Integer RSSI;

    @Column(name = "SIGNAL_LEVEL", nullable = false)
    private String signalLevel;

    @Column(name = "FREQUENCY", nullable = false)
    private Integer frequency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WIFI_REPORT_ID")
    private WifiAnalyzeReport wifiAnalyzeReport;
}
