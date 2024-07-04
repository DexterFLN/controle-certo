package br.com.controle.certo.infrastructure.config.resourceserver.authorizationserver;

import br.com.controle.certo.infrastructure.config.authorizationserver.AuthUser;
import br.com.controle.certo.infrastructure.config.authorizationserver.JwtCustomClaimsTokenEnhancer;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JwtCustomClaimsTokenEnhancerTest {

    @Test
    public void testEnhance() {
        // Mocking OAuth2Authentication and AuthUser
        OAuth2Authentication oAuth2Authentication = mock(OAuth2Authentication.class);
        AuthUser authUser = mock(AuthUser.class);
        when(oAuth2Authentication.getPrincipal()).thenReturn(authUser);
        when(authUser.getEmail()).thenReturn("test@example.com");

        // Creating the access token
        DefaultOAuth2AccessToken accessToken = new DefaultOAuth2AccessToken("test-token");

        // Enhancing the token
        JwtCustomClaimsTokenEnhancer enhancer = new JwtCustomClaimsTokenEnhancer();
        OAuth2AccessToken enhancedToken = enhancer.enhance(accessToken, oAuth2Authentication);

        // Assertions
        assertNotNull(enhancedToken);
        assertTrue(enhancedToken.getAdditionalInformation().containsKey("email"));
        assertEquals("test@example.com", enhancedToken.getAdditionalInformation().get("email"));
    }
}//teste do JWTCustom
