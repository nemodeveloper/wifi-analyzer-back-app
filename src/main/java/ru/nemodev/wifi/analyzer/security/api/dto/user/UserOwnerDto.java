package ru.nemodev.wifi.analyzer.security.api.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.nemodev.wifi.analyzer.core.api.dto.BaseEntityDto;
import ru.nemodev.wifi.analyzer.security.entity.user.User;

/**
 * created by simanov-an
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "User owner")
public class UserOwnerDto extends BaseEntityDto {

    @ApiModelProperty(value = "Login", required = true)
    private String login;

    public static UserOwnerDto fromEntity(User user) {
        UserOwnerDto userDto = new UserOwnerDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());

        return userDto;
    }

}
