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

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * {@link VaultConfig} demonstrates the use of VaultPropertySource.
 * 
 * Start the vault server using
 * 
 * <pre>
 * $ vault server -dev -dev-root-token-id=12345
 * </pre>
 * 
 * Populate the secrets with a client:
 * 
 * <pre>
 * export VAULT_ADDR=http://localhost:8200
 * vault write secret/hello-spring-vault username=rob password=secret
 * </pre>
 * 
 * @author Rob Winch
 * @see VaultConfig
 */
@SpringBootApplication
public class HelloSpringVaultApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringVaultApplication.class, args);
	}

	@Value("${username}")
	String username;
	
	@Value("${password}")
	String password;
	
	@PostConstruct
	public void run() {
		System.out.println("============================");
		System.out.println(username);
		System.out.println(password);
		System.out.println("============================");
	}
}
