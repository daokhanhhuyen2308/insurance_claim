package com.t3h.insurance_claim.config;

import com.t3h.insurance_claim.constant.DefaultRoles;
import com.t3h.insurance_claim.entity.RoleEntity;
import com.t3h.insurance_claim.entity.UserEntity;
import com.t3h.insurance_claim.repository.RoleRepository;
import com.t3h.insurance_claim.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Slf4j
@Component
public class ApplicationInitConfig implements ApplicationRunner{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${ADMIN_USER_NAME}")
    private String ADMIN_USER_NAME;

    @Value("${ADMIN_USER_PASSWORD}")
    private String ADMIN_USER_PASSWORD;

    public ApplicationInitConfig(UserRepository userRepository,
                                 RoleRepository roleRepository,
                                 @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Initializing ApplicationRunner...");

        if (userRepository.findUserByRoleName(DefaultRoles.ADMIN_ROLE).isEmpty() || !roleRepository.existsByName(DefaultRoles.ADMIN_ROLE)) {
            roleRepository.save(
                    RoleEntity.builder().name(DefaultRoles.USER_ROLE).code("User role").build());

            var roles = new HashSet<RoleEntity>();

            roles.add(RoleEntity.builder().name(DefaultRoles.ADMIN_ROLE).code("Admin role").build());

            UserEntity user =
                    UserEntity.builder()
                            .username(ADMIN_USER_NAME)
                            .email("admin@gmail.com")
                            .password(passwordEncoder.encode(ADMIN_USER_PASSWORD))
                            .roleEntities(roles)
                            .build();

            roleRepository.saveAll(roles);
            userRepository.save(user);
            log.error("Admin has been created with default. Please change it!");
        }

    }
}
