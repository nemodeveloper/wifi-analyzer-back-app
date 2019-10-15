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
@Table(name = "WIFI_ANALYZE_REPORTS")
public class WifiAnalyzeReport {

    @Id
    private String id;
}
