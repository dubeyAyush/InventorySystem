package com.invertorySystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuthConfig extends AuthorizationServerConfigurerAdapter {

	private String clientid = "testClient";
	
	@Value("${app.token.validity}")
	private int tokenValiditySeconds;
	/**
	 * client secret : my-secret-key
	 */
	private String clientSecret = "$2a$10$dHLi.wArP5BQsVA6zX9OieDyQWX5LKyuePzAoQ8nJwsn5FSrzxDYu";
	/**
	 * private key generate from OpenSSL
	 */
	private String privateKey = "MIIEowIBAAKCAQEAu7dUgAwQScETrQ5rxZC14S6q+/qUUeTZw0FBR7sb+C5q8dNo" + 
			"2hizxrt833EXpO5KI6w6JYctMq2aIjSef7KXNaScHmrv3xI7UOdKV0jCs56KvguU" + 
			"n4S34iMuvQR0d5o2rczkoetrgSgEuRlWd9VmLJ8nzJjcTihbS80jQ8JR/82H8HUm" + 
			"B+L2XfmukMs9gQxg9WZ2WG4rc1aPgurabVM2GCRqFoCGHVvRsOg9HimWo+exVIFU" + 
			"DtyJNeEqDc02RnW27ZP/b6LojgDY1aCEWBYRlknzItXsPZ3DICHC0mUNXDdRW1HT" + 
			"Q89HKrEpRF9is+H44YkDX2WF642BwDRoHEEHGQIDAQABAoIBAHsO1StYPTlMhKyX" + 
			"XplWo5UqddbYT3FcWFGDLttqUwygyWUc1YjcVsZ6VINabUinGh1Go5vEgSHO4tN3" + 
			"sUw/dJcrAVmDexq+pfoElhygS1tbHNM3XkgwxidOLy6khAGALEZFosBvR7uU03vQ" + 
			"m3nVgLTR5VJgB1SJakhIA81Az2akY0D09qWzI94ta56LAsRWe2qNUijy8XN7RNvb" + 
			"ObvR95T+utipJj0VtSr+xmoTbFTzeYlzY6WlZxMs2rDsuAFFG9EqxKt+BJSfGIuh" + 
			"kznUEkQ5woRS6NS84zpdyhyqDfdzhCsXXokOdzbvHqFhhznH/AyamgpmEQjxwG4L" + 
			"+fdNoIECgYEA69LaLa1JYl5hKeOHEUvHsLxDHnB4VTSgrRM/hm3YtKv2gGTjHd5n" + 
			"IU456AwvTVsKYYiN4B29ZtAMZkzhfLLFY76yK9sWsf24unePJp6c3wSWBAtzmWOS" + 
			"3Bzfm3NkrXJZx/aWkKZ5e+ShHrya+H+vvl4XA1vHP7dzyV21ABgtxqkCgYEAy8bM" + 
			"h8GGO6p/wu0790B1BSm+GKdeSHgWcR1QkaVBRUnYDNdi9bIzo+jvVRhOg8vGxHFn" + 
			"gJeA9WgjNGWGbcoSh0KBnqFldr1zL76RICnkkjui74CCZ2Yt0fEJb9TEPI7czllS" + 
			"0FmCGcar4qb9TFNXM+nsdSqd4+qEz+2uvEr8MvECgYBQ1/jpvcUxjs9ab+DG4ia0" + 
			"BOYxkjb+ZstKahXV/ORUk7suquddPvg1575cOYkhyLsXUpvon+llTJ9jlqmaeSLI" + 
			"HgCwniv6mtNwQTp279sxbT4MZuybQhJ/mPpjLTzvZQs4TOWy8w8klO1TPy8VT5is" + 
			"906GXTlCM57jAlfhj2hp+QKBgCLgnscpj2EM+JsTYa0bAixlDZ1IoDGFJEot+0lC" + 
			"YJ6MWgcwokZtPrtyNex5oY6bRSUAR/fRdogKT+Q68DxAVtWBw4+0Dhq5qEpmso4v" + 
			"vWht/qBBfZBXA8aoG7ZhU0kOJqrGzQ7OEx94SpkbfcUfipBfHFKdfbKxxBOWmc6u" + 
			"guXxAoGBAMGMbfepxBIWU15zghnSqEXbGaQYC1+lMDgmJhf5dHupT5n0TbgWCYKy" + 
			"dW36EIkRKaHnh/Z4Qpq/ZtoeU1P4tmfsRB+rFm3iWukExlEljvFnm4Y4247rF9BF" + 
			"Qzc5rL7HXvpByI6RVoEGo03Ousg6Jhu7RARfFPUpANoqF+Dmn1tE";
	
	/**
	 * public key generate from OpenSSL
	 */
	private String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu7dUgAwQScETrQ5rxZC1" + 
			"4S6q+/qUUeTZw0FBR7sb+C5q8dNo2hizxrt833EXpO5KI6w6JYctMq2aIjSef7KX" + 
			"NaScHmrv3xI7UOdKV0jCs56KvguUn4S34iMuvQR0d5o2rczkoetrgSgEuRlWd9Vm" + 
			"LJ8nzJjcTihbS80jQ8JR/82H8HUmB+L2XfmukMs9gQxg9WZ2WG4rc1aPgurabVM2" + 
			"GCRqFoCGHVvRsOg9HimWo+exVIFUDtyJNeEqDc02RnW27ZP/b6LojgDY1aCEWBYR" + 
			"lknzItXsPZ3DICHC0mUNXDdRW1HTQ89HKrEpRF9is+H44YkDX2WF642BwDRoHEEH" + 
			"GQIDAQAB";

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Bean
	public JwtAccessTokenConverter tokenEnhancer() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(privateKey);
		//converter.setVerifierKey(publicKey);
		return converter;
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(tokenEnhancer());
	}

	/**
	 * Using JWT Token 
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
		.authenticationManager(authenticationManager)
		.tokenStore(tokenStore())
		.accessTokenConverter(tokenEnhancer());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	/**
	 * Created in Memory client store no need for DataBase
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
		.inMemory()
		.withClient(clientid)
		.secret(clientSecret)
		.scopes("read", "write")
		.authorizedGrantTypes("password", "refresh_token")
		.accessTokenValiditySeconds(tokenValiditySeconds)
		.refreshTokenValiditySeconds(tokenValiditySeconds);

	}
	
	
}
