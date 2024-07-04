package br.com.controle.certo;

import br.com.controle.certo.infrastructure.config.Base64ProtocolResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.controle.certo")
public class ControleCertoApplication {

	public static void main(String[] args) {
		var app = new SpringApplication(ControleCertoApplication.class);
		app.addListeners(new Base64ProtocolResolver());
		app.run(args);
	}
}
