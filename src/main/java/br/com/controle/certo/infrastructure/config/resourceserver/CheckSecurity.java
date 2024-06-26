package br.com.controle.certo.infrastructure.config.resourceserver;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface CheckSecurity {
    @PreAuthorize("isAuthenticated()")
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface isAuthenticated {
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface hasAuthorityAdmin {
    }
}
