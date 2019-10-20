package ru.nemodev.wifi.analyzer.api.dto.wifi.report;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import ru.nemodev.wifi.analyzer.api.dto.BaseEntityDto;
import ru.nemodev.wifi.analyzer.api.dto.device.DeviceInfoDto;
import ru.nemodev.wifi.analyzer.api.dto.location.LocationDto;
import ru.nemodev.wifi.analyzer.api.dto.wifi.info.WifiAnalyzeInfoDto;
import ru.nemodev.wifi.analyzer.entity.wifi.report.WifiAnalyzeReport;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * created by simanov-an
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "Wifi analyze report")
public class WifiAnalyzeReportDto extends BaseEntityDto {

    @ApiModelProperty(value = "CreationDate")
    private LocalDateTime creationDate;

    @ApiModelProperty(value = "Comment")
    private String comment;

    @ApiModelProperty(value = "Location")
    private LocationDto location;

    @ApiModelProperty(value = "DeviceInfo", required = true)
    private DeviceInfoDto deviceInfo;

    @ApiModelProperty(value = "WifiAnalyzeInfoList", required = true)
    private List<WifiAnalyzeInfoDto> wifiAnalyzeInfoList;

    public WifiAnalyzeReport toEntity() {
        return toEntity(this);
    }

    public static WifiAnalyzeReport toEntity(WifiAnalyzeReportDto wifiAnalyzeReportDto) {
        WifiAnalyzeReport wifiAnalyzeReport = new WifiAnalyzeReport();
        wifiAnalyzeReport.setId(wifiAnalyzeReportDto.getId());
        wifiAnalyzeReport.setCreationDate(wifiAnalyzeReportDto.getCreationDate());
        wifiAnalyzeReport.setComment(wifiAnalyzeReportDto.getComment());
        wifiAnalyzeReport.setLocation(wifiAnalyzeReportDto.getLocation().toEntity());
        wifiAnalyzeReport.setDeviceInfo(DeviceInfoDto.toEntity(wifiAnalyzeReportDto.getDeviceInfo()));
        wifiAnalyzeReport.setWifiAnalyzeInfoList(
                wifiAnalyzeReportDto.getWifiAnalyzeInfoList().stream()
                        .map(wifiAnalyzeInfoDto -> wifiAnalyzeInfoDto.toEntity())
                        .collect(Collectors.toList()));

        return wifiAnalyzeReport;
    }

    public static List<WifiAnalyzeReportDto> fromList(List<WifiAnalyzeReport> wifiAnalyzeReports) {
        if (CollectionUtils.isEmpty(wifiAnalyzeReports)) {
            return List.of();
        }

        return wifiAnalyzeReports.stream()
                .map(WifiAnalyzeReportDto::fromEntity)
                .collect(Collectors.toList());
    }

    public static WifiAnalyzeReportDto fromEntity(WifiAnalyzeReport wifiAnalyzeReport) {
        WifiAnalyzeReportDto wifiAnalyzeReportDto = new WifiAnalyzeReportDto();
        wifiAnalyzeReportDto.setId(wifiAnalyzeReport.getId());
        wifiAnalyzeReportDto.setCreationDate(wifiAnalyzeReport.getCreationDate());
        wifiAnalyzeReportDto.setComment(wifiAnalyzeReport.getComment());
        wifiAnalyzeReportDto.setLocation(LocationDto.fromEntity(wifiAnalyzeReport.getLocation()));
        wifiAnalyzeReportDto.setDeviceInfo(DeviceInfoDto.fromEntity(wifiAnalyzeReport.getDeviceInfo()));
        wifiAnalyzeReportDto.setWifiAnalyzeInfoList(
                wifiAnalyzeReport.getWifiAnalyzeInfoList().stream()
                        .map(WifiAnalyzeInfoDto::fromEntity)
                        .collect(Collectors.toList()));

        return wifiAnalyzeReportDto;
    }

}
