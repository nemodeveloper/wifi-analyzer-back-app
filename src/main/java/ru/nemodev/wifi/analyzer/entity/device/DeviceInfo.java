package ru.nemodev.wifi.analyzer.entity.device;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nemodev.wifi.analyzer.core.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * created by simanov-an
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "DEVICES_INFO")
public class DeviceInfo extends BaseEntity {

    @Column(name = "DEVICE", nullable = false)
    private String device;

    @Column(name = "MODEL", nullable = false)
    private String model;

    @Column(name = "VERSION_OS", nullable = false)
    private String versionOS;

    @Column(name = "MAC_ADDRESS", nullable = false)
    private String macAddress;

    @Column(name = "IP_ADDRESS", nullable = false)
    private String ipAddress;

}
