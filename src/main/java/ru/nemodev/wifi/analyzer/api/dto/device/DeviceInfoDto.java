package ru.nemodev.wifi.analyzer.api.dto.device;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nemodev.wifi.analyzer.api.dto.BaseEntityDto;
import ru.nemodev.wifi.analyzer.entity.device.DeviceInfo;

/**
 * created by simanov-an
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "Device info")
public class DeviceInfoDto extends BaseEntityDto {

    @ApiModelProperty(value = "Name", required = true)
    private String name;

    public DeviceInfo toEntity() {
        return toEntity(this);
    }

    public static DeviceInfo toEntity(DeviceInfoDto deviceInfoDto) {
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setId(deviceInfoDto.getId());
        deviceInfo.setName(deviceInfoDto.getName());

        return deviceInfo;
    }

    public static DeviceInfoDto fromEntity(DeviceInfo deviceInfo) {
        DeviceInfoDto deviceInfoDto = new DeviceInfoDto();
        deviceInfoDto.setId(deviceInfo.getId());
        deviceInfoDto.setName(deviceInfo.getName());

        return deviceInfoDto;
    }
}
