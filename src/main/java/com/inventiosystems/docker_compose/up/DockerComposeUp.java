package com.inventiosystems.docker_compose.up;

/**
 * Object providing DSL for docker-compose up command.
 */
public class DockerComposeUp {

    private static final String COMMAND = "up";

    private static final String DETACH = " --detach";

    private String currentCommand;

    public DockerComposeUp (String existingCommand) {
        currentCommand = existingCommand + " " + COMMAND;
    }

    public DockerComposeUp detach () {
        currentCommand = currentCommand + DETACH;
        return this;
    }

    @Override
    public String toString () {
        return currentCommand;
    }
}
