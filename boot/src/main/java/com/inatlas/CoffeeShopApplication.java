package com.inatlas;

import com.inatlas.domain.usecase.command.Invoker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class CoffeeShopApplication {

  public CoffeeShopApplication() {
  }

  public static void main(String[] args) {

    new SpringApplicationBuilder(CoffeeShopApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

  }

}
