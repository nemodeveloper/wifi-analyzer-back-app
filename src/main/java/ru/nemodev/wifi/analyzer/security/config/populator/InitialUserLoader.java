package ru.nemodev.wifi.analyzer.security.config.populator;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.nemodev.wifi.analyzer.security.entity.oauth.AuthClientDetail;
import ru.nemodev.wifi.analyzer.security.entity.oauth.GrantType;
import ru.nemodev.wifi.analyzer.security.entity.privilege.Privilege;
import ru.nemodev.wifi.analyzer.security.entity.role.Role;
import ru.nemodev.wifi.analyzer.security.entity.user.User;
import ru.nemodev.wifi.analyzer.security.service.oauth.AuthClientDetailService;
import ru.nemodev.wifi.analyzer.security.service.privilege.PrivilegeService;
import ru.nemodev.wifi.analyzer.security.service.role.RoleService;
import ru.nemodev.wifi.analyzer.security.service.user.UserService;

import java.util.Arrays;
import java.util.List;


@Component
public class InitialUserLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final UserService userService;
    private final RoleService roleService;
    private final PrivilegeService privilegeService;
    private final AuthClientDetailService authClientDetailService;
    private final PasswordEncoder passwordEncoder;

    public InitialUserLoader(UserService userService, RoleService roleService,
                             PrivilegeService privilegeService, AuthClientDetailService authClientDetailService,
                             PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.privilegeService = privilegeService;
        this.authClientDetailService = authClientDetailService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        Role role = new Role();
        role.setName(Role.NAME_PREF + "ADMIN");
        if (roleService.findByName(role.getName()).isEmpty()) {

            Privilege privilege = new Privilege();
            privilege.setName(Privilege.NAME_PREF + "HELLO");
            privilegeService.create(privilege);
            role.setPrivileges(List.of(privilege));

            roleService.create(role);
        }

        if (userService.findByLogin("admin").isEmpty()) {
            User user = new User();
            user.setLogin("admin");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setEmail("test@gmail.com");
            user.setEnabled(true);
            user.setRoles(Arrays.asList(role));

            userService.create(user);
        }

        if (authClientDetailService.findByClientId("mobile").isEmpty()) {
            AuthClientDetail authClientDetail = new AuthClientDetail();
            authClientDetail.setClientId("mobile");
            authClientDetail.setClientSecret("$2a$10$Qh9Cjo0w4nMBSsrjML0n0OLLwcBxRGg2mrWs403kKE4sIorqJHq5y");
            authClientDetail.setAuthorizedGrantTypes(GrantType.getAllGrantTypes());
            authClientDetail.setScope("read,write");
            authClientDetail.setAccessTokenValidity(900);
            authClientDetail.setRefreshTokenValidity(960);

            authClientDetailService.create(authClientDetail);
        }
    }
}
