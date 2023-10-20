package com.inatlas.config;

import com.inatlas.domain.usecase.command.Command;
import com.inatlas.domain.usecase.command.Invoker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;

import java.util.Map;

@Configuration
 @Profile("command-line-runner")
public class CommandLineConfiguration {


//  @Profile("command-line-runner")
  @Bean
  public CommandLineRunner commandLineRunner(Invoker invoker) {
    return args -> {
      while(true) {
        invoker.execute("principal");
      }
    };
  }

}
