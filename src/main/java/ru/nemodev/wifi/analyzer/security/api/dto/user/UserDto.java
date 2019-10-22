package ru.nemodev.wifi.analyzer.security.api.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import ru.nemodev.wifi.analyzer.core.api.dto.BaseEntityDto;
import ru.nemodev.wifi.analyzer.security.entity.user.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * created by simanov-an
 */
@Getter
@Setter
@NoArgsConstructor
@ApiModel(value = "User info")
public class UserDto extends BaseEntityDto {

    @ApiModelProperty(value = "CreationDate", required = true)
    private LocalDateTime creationDate;

    @ApiModelProperty(value = "Login", required = true)
    private String login;

    @ApiModelProperty(value = "Enabled", required = true)
    private boolean enabled;

    @ApiModelProperty(value = "AuthorityList", required = true)
    private List<String> authorities;

    public static UserDto fromEntity(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setCreationDate(user.getCreationDate());
        userDto.setLogin(user.getLogin());
        userDto.setEnabled(user.isEnabled());
        userDto.setAuthorities(user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        return userDto;
    }

    public static List<UserDto> fromEntityList(List<User> users) {
        if (CollectionUtils.isEmpty(users)) {
            return List.of();
        }

        return users.stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

}
