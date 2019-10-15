package ru.nemodev.wifi.analyzer.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * created by simanov-an
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "DEVICES_INFO")
public class DeviceInfo {

    @Id
    private String id;

}
