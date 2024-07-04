package br.com.controle.certo.infrastructure.config.resourceserver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Collections;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@ExtendWith(MockitoExtension.class)
public class AuthJwtClaimTest {

    @Mock
    private JwtDecoder jwtDecoder;

    @InjectMocks
    private AuthJwtClaim authJwtClaim;

    @BeforeEach
    void setUp() {
        // Reset the security context before each test
        SecurityContextHolder.clearContext();
    }

    @Test
    void testGetDocumentNumberUserFromJwt() {
        // Mocking JWT
        Map<String, Object> claims = Collections.singletonMap("user_name", "12345678901");
        Jwt jwt = new Jwt("tokenValue", null, null, claims, claims);
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(jwt);

        // Setting SecurityContext
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(jwtAuthenticationToken);
        SecurityContextHolder.setContext(securityContext);

        // Calling the method under test
        String documentNumber = authJwtClaim.getDocumentNumberUserFromJwt();

        // Asserting the result
        assertEquals("12345678901", documentNumber);
    }
}
