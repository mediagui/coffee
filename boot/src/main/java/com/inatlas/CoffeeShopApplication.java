package com.inatlas;

import com.inatlas.domain.usecase.command.Invoker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
public class CoffeeShopApplication {

  public CoffeeShopApplication() {
  }

  public static void main(String[] args) {

    SpringApplication.run(CoffeeShopApplication.class, args);

  }

}
