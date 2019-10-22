package ru.nemodev.wifi.analyzer.security.config.populator;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.nemodev.wifi.analyzer.security.entity.oauth.AuthClientDetail;
import ru.nemodev.wifi.analyzer.security.entity.oauth.GrantType;
import ru.nemodev.wifi.analyzer.security.entity.role.Role;
import ru.nemodev.wifi.analyzer.security.entity.role.RoleType;
import ru.nemodev.wifi.analyzer.security.entity.user.User;
import ru.nemodev.wifi.analyzer.security.service.oauth.AuthClientDetailService;
import ru.nemodev.wifi.analyzer.security.service.privilege.PrivilegeService;
import ru.nemodev.wifi.analyzer.security.service.role.RoleService;
import ru.nemodev.wifi.analyzer.security.service.user.UserService;


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
        adminRole.setName(RoleType.ADMIN.getRole());
        // TODO оптимизировать поиск по existsByName
        if (roleService.findByName(adminRole.getName()).isEmpty()) {
            roleService.create(adminRole);
        }

        if (!userService.existsByLogin("admin")) {
            User user = new User();
            user.setLogin("admin");
            userService.create(user, "1234", true);
        }

        // ANALYZER USER
        Role analyzerRole = new Role();
        analyzerRole.setName(RoleType.ANALYZER.getRole());
        if (roleService.findByName(analyzerRole.getName()).isEmpty()) {
            roleService.create(analyzerRole);
        }

        if (!userService.existsByLogin("analyzer")) {
            User user = new User();
            user.setLogin("analyzer");
            userService.create(user, "1234", false);
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
