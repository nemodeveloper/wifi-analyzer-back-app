package ru.nemodev.wifi.analyzer.entity.wifi_report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nemodev.wifi.analyzer.core.entity.BaseEntity;
import ru.nemodev.wifi.analyzer.entity.device.DeviceInfo;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * created by simanov-an
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "WIFI_ANALYZE_REPORTS")
public class WifiAnalyzeReport extends BaseEntity {

    @Column(name = "CREATION_DATE", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "SSID", nullable = false)
    private String ssid;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "DEVICE_ID")
    private DeviceInfo deviceInfo;
}
