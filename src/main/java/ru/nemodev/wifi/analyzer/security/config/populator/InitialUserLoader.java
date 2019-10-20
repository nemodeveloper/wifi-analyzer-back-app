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
        createUsers();
        createOAuthClients();
    }

    private void createUsers() {

        // SUPER USER
        Role adminRole = new Role();
        adminRole.setName(Role.NAME_PREF + "ADMIN");
        if (roleService.findByName(adminRole.getName()).isEmpty()) {
            roleService.create(adminRole);
        }

        if (userService.findByLogin("admin").isEmpty()) {
            User user = new User();
            user.setLogin("admin");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setEmail("rest19930705@gmail.com");
            user.setEnabled(true);
            user.setRoles(Arrays.asList(adminRole));

            userService.create(user);
        }

        // ANALYZER USER
        Role analyzerRole = new Role();
        analyzerRole.setName(Role.NAME_PREF + "ANALYZER");
        if (roleService.findByName(analyzerRole.getName()).isEmpty()) {
            roleService.create(analyzerRole);
        }

        if (userService.findByLogin("analyzer").isEmpty()) {
            User user = new User();
            user.setLogin("analyzer");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setEmail("analyzer@gmail.com");
            user.setEnabled(true);
            user.setRoles(Arrays.asList(analyzerRole));

            userService.create(user);
        }

    }

    private void createOAuthClients() {
        if (authClientDetailService.findByClientId("mobile").isEmpty()) {
            AuthClientDetail mobile = new AuthClientDetail();
            mobile.setClientId("mobile");
            mobile.setClientSecret(passwordEncoder.encode("1234"));
            mobile.setAuthorizedGrantTypes(GrantType.getAllGrantTypes());
            mobile.setScope("read,write");
            mobile.setAccessTokenValidity(900);
            mobile.setRefreshTokenValidity(960);

            authClientDetailService.create(mobile);
        }

        if (authClientDetailService.findByClientId("web").isEmpty()) {
            AuthClientDetail web = new AuthClientDetail();
            web.setClientId("web");
            web.setClientSecret(passwordEncoder.encode("1234"));
            web.setAuthorizedGrantTypes(GrantType.getAllGrantTypes());
            web.setScope("read,write");
            web.setAccessTokenValidity(1800);
            web.setRefreshTokenValidity(2000);

            authClientDetailService.create(web);
        }
    }
}
