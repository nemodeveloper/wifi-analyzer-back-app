package ru.nemodev.wifi.analyzer.api.dto.wifi.info;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nemodev.wifi.analyzer.api.dto.BaseEntityDto;
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

    @ApiModelProperty(value = "ssid", required = true)
    private String ssid;

    public WifiAnalyzeInfo toEntity() {
        return toEntity(this);
    }

    public static WifiAnalyzeInfo toEntity(WifiAnalyzeInfoDto wifiAnalyzeInfoDto) {
        WifiAnalyzeInfo wifiAnalyzeInfo = new WifiAnalyzeInfo();
        wifiAnalyzeInfo.setId(wifiAnalyzeInfoDto.getId());
        wifiAnalyzeInfo.setSsid(wifiAnalyzeInfoDto.getSsid());
        wifiAnalyzeInfo.setCreationDate(wifiAnalyzeInfoDto.getCreationDate());

        return wifiAnalyzeInfo;
    }

    public static WifiAnalyzeInfoDto fromEntity(WifiAnalyzeInfo wifiAnalyzeInfo) {
        WifiAnalyzeInfoDto wifiAnalyzeInfoDto = new WifiAnalyzeInfoDto();
        wifiAnalyzeInfoDto.setId(wifiAnalyzeInfo.getId());
        wifiAnalyzeInfoDto.setCreationDate(wifiAnalyzeInfo.getCreationDate());
        wifiAnalyzeInfoDto.setSsid(wifiAnalyzeInfo.getSsid());

        return wifiAnalyzeInfoDto;
    }
}
