package ru.nemodev.wifi.analyzer.api.dto.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nemodev.wifi.analyzer.core.api.dto.BaseEntityDto;
import ru.nemodev.wifi.analyzer.entity.device.DeviceInfo;

/**
 * created by simanov-an
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "Device info")
public class DeviceInfoDto extends BaseEntityDto {

    @ApiModelProperty(value = "device", required = true)
    private String device;

    @ApiModelProperty(value = "Model", required = true)
    private String model;

    @ApiModelProperty(value = "Version OS", required = true)
    private String versionOS;

    @ApiModelProperty(value = "MacAddress", required = true)
    private String macAddress;

    @ApiModelProperty(value = "IpAddress", required = true)
    private String ipAddress;

    public DeviceInfo toEntity() {
        return toEntity(this);
    }

    public static DeviceInfo toEntity(DeviceInfoDto deviceInfoDto) {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setId(deviceInfoDto.getId());
        deviceInfo.setDevice(deviceInfoDto.getDevice());
        deviceInfo.setModel(deviceInfoDto.getModel());
        deviceInfo.setVersionOS(deviceInfoDto.getVersionOS());
        deviceInfo.setMacAddress(deviceInfoDto.getMacAddress());
        deviceInfo.setIpAddress(deviceInfoDto.getIpAddress());

        return deviceInfo;
    }

    public static DeviceInfoDto fromEntity(DeviceInfo deviceInfo) {
        DeviceInfoDto deviceInfoDto = new DeviceInfoDto();
        deviceInfoDto.setId(deviceInfo.getId());
        deviceInfoDto.setDevice(deviceInfo.getDevice());
        deviceInfoDto.setModel(deviceInfo.getModel());
        deviceInfoDto.setVersionOS(deviceInfo.getVersionOS());
        deviceInfoDto.setMacAddress(deviceInfo.getMacAddress());
        deviceInfoDto.setIpAddress(deviceInfo.getIpAddress());

        return deviceInfoDto;
    }
}
