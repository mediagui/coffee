/**
 * The Invoker class is responsible for executing commands based on their names (Command Pattern).
 * It contains a map of command names and their corresponding commands.
 * The execute method takes a command name as input and executes the corresponding command if it exists in the map.
 */
package com.inatlas.domain.usecase.command;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
 @Profile("command-line-runner")
public class Invoker {
  private final Map<String, Command> commands;

  /**
   * Constructs an Invoker object with a map of commands.
   * @param commands a map of command names and their corresponding commands
   */
  @Lazy
  public Invoker(Map<String, Command> commands) {
    this.commands = commands;
  }

  /**
   * Executes a command based on its name.
   * @param commandName the name of the command to be executed
   */
  public void execute(String commandName) {
    if (commands.containsKey(commandName)) {
      commands.get(commandName).execute();
    }
  }
}
