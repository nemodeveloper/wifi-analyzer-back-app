package ru.nemodev.wifi.analyzer.security.config.populator;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.nemodev.wifi.analyzer.security.entity.oauth.AuthClientDetail;
import ru.nemodev.wifi.analyzer.security.entity.oauth.GrantType;
import ru.nemodev.wifi.analyzer.security.entity.role.Role;
import ru.nemodev.wifi.analyzer.security.entity.user.User;
import ru.nemodev.wifi.analyzer.security.service.oauth.AuthClientDetailService;
import ru.nemodev.wifi.analyzer.security.service.privilege.PrivilegeService;
import ru.nemodev.wifi.analyzer.security.service.role.RoleService;
import ru.nemodev.wifi.analyzer.security.service.user.UserService;

import java.util.Arrays;


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
            roleService.create(role);
        }

        if (userService.findByLogin("admin").isEmpty()) {
            User user = new User();
            user.setLogin("admin");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setEmail("rest19930705@gmail.com");
            user.setEnabled(true);
            user.setRoles(Arrays.asList(role));

            userService.create(user);
        }

        if (authClientDetailService.findByClientId("mobile").isEmpty()) {
            AuthClientDetail mobile = new AuthClientDetail();
            mobile.setClientId("mobile");
            mobile.setClientSecret("$2a$10$Qh9Cjo0w4nMBSsrjML0n0OLLwcBxRGg2mrWs403kKE4sIorqJHq5y");
            mobile.setAuthorizedGrantTypes(GrantType.getAllGrantTypes());
            mobile.setScope("read,write");
            mobile.setAccessTokenValidity(900);
            mobile.setRefreshTokenValidity(960);

            authClientDetailService.create(mobile);
        }

        if (authClientDetailService.findByClientId("web").isEmpty()) {
            AuthClientDetail web = new AuthClientDetail();
            web.setClientId("web");
            web.setClientSecret("$2a$10$Qh9Cjo0w4nMBSsrjML0n0OLLwcBxRGg2mrWs403kKE4sIorqJHq5y");
            web.setAuthorizedGrantTypes(GrantType.getAllGrantTypes());
            web.setScope("read,write");
            web.setAccessTokenValidity(1800);
            web.setRefreshTokenValidity(2000);

            authClientDetailService.create(web);
        }

    }
}
