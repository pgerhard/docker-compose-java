package com.inventiosystems.docker_compose.stop;

import com.inventiosystems.docker_compose.HasCommand;

/**
 * Object providing DSL for docker-compose stop command
 */
public class DockerComposeStop implements HasCommand {

    private static final String COMMAND = "stop";

    private String currentCommand;

    public DockerComposeStop ( String existingCommand ) {
        this.currentCommand = existingCommand + " " + COMMAND;
    }

    @Override
    public String command () {
        return currentCommand;
    }
}


