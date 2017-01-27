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

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * {@link VaultConfig} demonstrates the use of VaultPropertySource to
 * automatically obtain credentials for a database.
 * </p>
 * 
 * <p>
 * It is important to note that this sample does not currently renew the
 * secrets. This means that the credentials will eventually expire and prevent
 * the application from connecting to the database. For details refer to
 * https://github.com/spring-projects/spring-vault/issues/50
 * </p>
 * 
 * Start the vault server using
 * 
 * <pre>
 * $ vault server -dev -dev-root-token-id=12345
 * </pre>
 * 
 * Setup a user for Vault to use to Mount the MySQL Server
 * 
 * <pre>
 * $ mysql -u root -p
 * mysql> CREATE USER 'vault'@'localhost' IDENTIFIED BY 'password';
 * mysql> GRANT ALL PRIVILEGES ON *.* TO 'vault'@'localhost' WITH GRANT OPTION;
 * </pre>
 * 
 * Create the database spring and a table messages
 * 
 * <pre>
 * mysql> create database spring;
 * mysql> use spring;
 * mysql> create table message(id int not null auto_increment, text varchar(256), primary key (id));
 * mysql> insert into message(text) values ('Hi Spring Vault!');
 * </pre>
 * 
 * Mount a MySQL Server
 * 
 * <pre>
 * export VAULT_ADDR=http://localhost:8200
 * vault mount mysql
 * vault write mysql/config/connection connection_url="vault:password@tcp(localhost:3306)/"
 * vault write mysql/roles/readonly sql="CREATE USER '{{name}}'@'%' IDENTIFIED BY '{{password}}';GRANT SELECT ON *.* TO '{{name}}'@'%';"
 * vault read mysql/creds/readonly
 * </pre>
 * 
 * @author Rob Winch
 * @see VaultConfig
 */
@SpringBootApplication
public class HelloSpringVaultMySqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringVaultMySqlApplication.class, args);
	}
}
