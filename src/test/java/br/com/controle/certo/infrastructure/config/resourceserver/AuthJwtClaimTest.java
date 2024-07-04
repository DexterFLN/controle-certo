package br.com.controle.certo.infrastructure.config.resourceserver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Collections;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
@ExtendWith(MockitoExtension.class)
public class AuthJwtClaimTest {
    @InjectMocks
    private AuthJwtClaim authJwtClaim;
    @BeforeEach
    void setUp() {
        SecurityContextHolder.clearContext();
    }
    @Test
    void testGetDocumentNumberUserFromJwt() {
        Map<String, Object> claims = Collections.singletonMap("user_name", "12345678901");
        Jwt jwt = new Jwt("tokenValue", null, null, claims, claims);
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(jwt);

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(jwtAuthenticationToken);
        SecurityContextHolder.setContext(securityContext);

        String documentNumber = authJwtClaim.getDocumentNumberUserFromJwt();

        assertEquals("12345678901", documentNumber);
    }
}
