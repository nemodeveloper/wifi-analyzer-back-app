package ru.nemodev.wifi.analyzer.entity.wifi.report;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.nemodev.wifi.analyzer.core.entity.BaseEntity;
import ru.nemodev.wifi.analyzer.entity.device.DeviceInfo;
import ru.nemodev.wifi.analyzer.entity.location.Location;
import ru.nemodev.wifi.analyzer.entity.wifi.info.WifiAnalyzeInfo;
import ru.nemodev.wifi.analyzer.security.entity.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "COMMENT")
    private String comment;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OWNER_USER_ID", nullable = false)
    @Fetch(FetchMode.SELECT)
    private User ownerUser;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "LOCATION_ID", nullable = false)
    private Location location;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "DEVICE_ID", nullable = false)
    private DeviceInfo deviceInfo;

    @OneToMany(mappedBy = "wifiAnalyzeReport", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WifiAnalyzeInfo> wifiAnalyzeInfoList = new ArrayList<>();

    public void addWifiAnalyzeInfo(WifiAnalyzeInfo wifiAnalyzeInfo) {
        wifiAnalyzeInfoList.add(wifiAnalyzeInfo);
        wifiAnalyzeInfo.setWifiAnalyzeReport(this);
    }

}
