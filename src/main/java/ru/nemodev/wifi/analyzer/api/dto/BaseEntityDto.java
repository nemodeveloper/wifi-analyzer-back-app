package ru.nemodev.wifi.analyzer.api.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * created by simanov-an
 */
@Getter
@Setter
public abstract class BaseEntityDto {

    @ApiModelProperty(value = "Id")
    protected String id;

}
