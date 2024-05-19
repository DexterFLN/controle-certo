package br.com.controle.certo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "br.com.controle.certo")
public class ControleCertoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ControleCertoApplication.class, args);
	}

}
