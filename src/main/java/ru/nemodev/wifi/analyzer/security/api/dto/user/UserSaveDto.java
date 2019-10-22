package ru.nemodev.wifi.analyzer.security.api.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nemodev.wifi.analyzer.core.api.dto.BaseEntityDto;

/**
 * created by simanov-an
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "User info")
public class UserSaveDto extends BaseEntityDto {

    @ApiModelProperty(value = "Login", required = true)
    private String login;

    @ApiModelProperty(value = "Login", required = true)
    private String password;

    @ApiModelProperty(value = "Is admin")
    private boolean admin;

}
