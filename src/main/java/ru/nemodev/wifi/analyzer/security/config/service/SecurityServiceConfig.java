package ru.nemodev.wifi.analyzer.security.config.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.nemodev.wifi.analyzer.security.repository.oauth.AuthClientDetailRepository;
import ru.nemodev.wifi.analyzer.security.repository.privilege.PrivilegeRepository;
import ru.nemodev.wifi.analyzer.security.repository.role.RoleRepository;
import ru.nemodev.wifi.analyzer.security.repository.user.UserRepository;
import ru.nemodev.wifi.analyzer.security.service.oauth.AuthClientDetailService;
import ru.nemodev.wifi.analyzer.security.service.oauth.AuthClientDetailServiceImpl;
import ru.nemodev.wifi.analyzer.security.service.privilege.PrivilegeService;
import ru.nemodev.wifi.analyzer.security.service.privilege.PrivilegeServiceImpl;
import ru.nemodev.wifi.analyzer.security.service.role.RoleService;
import ru.nemodev.wifi.analyzer.security.service.role.RoleServiceImpl;
import ru.nemodev.wifi.analyzer.security.service.user.UserService;
import ru.nemodev.wifi.analyzer.security.service.user.UserServiceImpl;

@Configuration
public class SecurityServiceConfig
{
    private final UserRepository userRepository;
    private final PrivilegeRepository privilegeRepository;
    private final RoleRepository roleRepository;
    private final AuthClientDetailRepository authClientDetailRepository;

    public SecurityServiceConfig(UserRepository userRepository,
                                 PrivilegeRepository privilegeRepository,
                                 RoleRepository roleRepository,
                                 AuthClientDetailRepository authClientDetailRepository)
    {
        this.userRepository = userRepository;
        this.privilegeRepository = privilegeRepository;
        this.roleRepository = roleRepository;
        this.authClientDetailRepository = authClientDetailRepository;
    }

    @Bean
    public UserService userService()
    {
        return new UserServiceImpl(userRepository);
    }

    @Bean
    public AuthClientDetailService authClientDetailService() {
        return new AuthClientDetailServiceImpl(authClientDetailRepository);
    }

    @Bean
    public PrivilegeService privilegeService() {
        return new PrivilegeServiceImpl(privilegeRepository);
    }

    @Bean
    public RoleService roleService() {
        return new RoleServiceImpl(roleRepository);
    }

}
