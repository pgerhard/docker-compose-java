package com.inventiosystems.docker_compose.stop;

/**
 * Object providing DSL for docker-compose stop command
 */
public class DockerComposeStop {

    private static final String COMMAND = "stop";

    private String currentCommand;

    public DockerComposeStop ( String existingCommand ) {
        this.currentCommand = existingCommand + " " + COMMAND;
    }

    @Override
    public String toString () {
        return currentCommand;
    }
}


