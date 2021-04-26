package com.example.demo.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.demo.security.ApplicationUserRole.*;

@Repository("fake")
public class FakeApplicationDaoService implements ApplicationUserDao{
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return this.getApplicationUsers().stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser("annasmith",
                        passwordEncoder.encode("password"),
                        STUDENT.getGratedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser("Linda",
                        passwordEncoder.encode("password123"),
                        ADMIN.getGratedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUser("Tom",
                        passwordEncoder.encode("password"),
                        ADMINTRAINEE.getGratedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );

        return applicationUsers;
    }

}
