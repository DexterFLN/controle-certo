package br.com.controle.certo.infrastructure.config.resourceserver;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthJwtClaimTest {

    @Mock
    private JwtDecoder jwtDecoder;

    @InjectMocks
    private AuthJwtClaim authJwtClaim;

    @Test
    void testGetDocumentNumberUserFromJwt() {
        // Mocking JWT
        Map<String, Object> claims = Collections.singletonMap("document_number", "123456789");
        Jwt jwt = new Jwt("tokenValue", null, null, claims, claims);
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(jwt);

        // Setting SecurityContext
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(jwtAuthenticationToken);
        SecurityContextHolder.setContext(securityContext);

        // Calling the method under test
        String documentNumber = authJwtClaim.getDocumentNumberUserFromJwt();

        // Asserting the result
        assertEquals("123456789", documentNumber);
    }
}
