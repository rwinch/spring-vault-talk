/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sample;

/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.net.URI;

import org.springframework.context.annotation.Configuration;
import org.springframework.vault.annotation.VaultPropertySource;
import org.springframework.vault.authentication.ClientAuthentication;
import org.springframework.vault.authentication.TokenAuthentication;
import org.springframework.vault.client.VaultEndpoint;
import org.springframework.vault.config.AbstractVaultConfiguration;

/**
 * Loads the properties from secret/hello-spring-vault with
 * {@link VaultPropertySource}.
 * 
 * @author Rob Winch
 * @see HelloSpringVaultApplication
 */
@Configuration
@VaultPropertySource("secret/hello-spring-vault")
public class VaultConfig extends AbstractVaultConfiguration {
	/**
	 * Specify an endpoint for connecting to Vault.
	 */
	@Override
	public VaultEndpoint vaultEndpoint() {
		String uri = getEnvironment().getProperty("vault.uri");
		if (uri != null) {
			return VaultEndpoint.from(URI.create(uri));
		}
		// ...
		throw new IllegalStateException();
	}

	/**
	 * Configure a client authentication. Please consider a more secure
	 * authentication method for production use.
	 */
	@Override
	public ClientAuthentication clientAuthentication() {
		// ...
		String token = getEnvironment().getProperty("vault.token");
		if (token != null) {
			return new TokenAuthentication(token);
		}
		// ...
		throw new IllegalStateException();
	}
}
