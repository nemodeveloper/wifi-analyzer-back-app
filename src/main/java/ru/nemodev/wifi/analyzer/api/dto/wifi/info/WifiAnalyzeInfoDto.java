package ru.nemodev.wifi.analyzer.api.dto.wifi.info;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nemodev.wifi.analyzer.core.api.dto.BaseEntityDto;
import ru.nemodev.wifi.analyzer.entity.wifi.info.WifiAnalyzeInfo;

import java.time.LocalDateTime;

/**
 * created by simanov-an
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "Wifi analyze info")
public class WifiAnalyzeInfoDto extends BaseEntityDto {

    @ApiModelProperty(value = "CreationDate", required = true)
    private LocalDateTime creationDate;

    @ApiModelProperty(value = "SSID", required = true)
    private String ssid;

    @ApiModelProperty(value = "BSSID", required = true)
    private String bssid;

    @ApiModelProperty(value = "RSSI", required = true)
    private Integer rssi;

    @ApiModelProperty(value = "Signal level", required = true)
    private String signalLevel;

    @ApiModelProperty(value = "Frequency", required = true)
    private Integer frequency;

    @ApiModelProperty(value = "Verbose speed", required = true)
    private String speed;

    public WifiAnalyzeInfo toEntity() {
        return toEntity(this);
    }

    public static WifiAnalyzeInfo toEntity(WifiAnalyzeInfoDto wifiAnalyzeInfoDto) {
        WifiAnalyzeInfo wifiAnalyzeInfo = new WifiAnalyzeInfo();
        wifiAnalyzeInfo.setId(wifiAnalyzeInfoDto.getId());
        wifiAnalyzeInfo.setCreationDate(wifiAnalyzeInfoDto.getCreationDate());
        wifiAnalyzeInfo.setSSID(wifiAnalyzeInfoDto.getSsid());
        wifiAnalyzeInfo.setBSSID(wifiAnalyzeInfoDto.getBssid());
        wifiAnalyzeInfo.setRSSI(wifiAnalyzeInfoDto.getRssi());
        wifiAnalyzeInfo.setSignalLevel(wifiAnalyzeInfoDto.getSignalLevel());
        wifiAnalyzeInfo.setFrequency(wifiAnalyzeInfoDto.getFrequency());
        wifiAnalyzeInfo.setSpeed(wifiAnalyzeInfoDto.getSpeed());

        return wifiAnalyzeInfo;
    }

    public static WifiAnalyzeInfoDto fromEntity(WifiAnalyzeInfo wifiAnalyzeInfo) {
        WifiAnalyzeInfoDto wifiAnalyzeInfoDto = new WifiAnalyzeInfoDto();
        wifiAnalyzeInfoDto.setId(wifiAnalyzeInfo.getId());
        wifiAnalyzeInfoDto.setCreationDate(wifiAnalyzeInfo.getCreationDate());
        wifiAnalyzeInfoDto.setSsid(wifiAnalyzeInfo.getSSID());
        wifiAnalyzeInfoDto.setBssid(wifiAnalyzeInfo.getBSSID());
        wifiAnalyzeInfoDto.setRssi(wifiAnalyzeInfo.getRSSI());
        wifiAnalyzeInfoDto.setSignalLevel(wifiAnalyzeInfo.getSignalLevel());
        wifiAnalyzeInfoDto.setFrequency(wifiAnalyzeInfo.getFrequency());
        wifiAnalyzeInfoDto.setSpeed(wifiAnalyzeInfo.getSpeed());

        return wifiAnalyzeInfoDto;
    }
}
