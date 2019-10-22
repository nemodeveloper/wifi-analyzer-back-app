package ru.nemodev.wifi.analyzer.api.dto.location;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nemodev.wifi.analyzer.core.api.dto.BaseEntityDto;
import ru.nemodev.wifi.analyzer.entity.location.Location;

/**
 * created by simanov-an
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "Location info")
public class LocationDto extends BaseEntityDto {

    @ApiModelProperty(value = "Name", required = true)
    private String name;

    public Location toEntity() {
        return toEntity(this);
    }

    public static Location toEntity(LocationDto locationDto) {
        Location location = new Location();
        location.setId(locationDto.getId());
        location.setName(locationDto.getName());

        return location;
    }

    public static LocationDto fromEntity(Location location) {
        LocationDto locationDto = new LocationDto();
        locationDto.setId(location.getId());
        locationDto.setName(location.getName());

        return locationDto;
    }
}
