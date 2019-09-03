package com.inventiosystems.docker_compose.down;

import com.inventiosystems.docker_compose.HasCommand;

public class DockerComposeDown implements HasCommand {

    private static final String COMMAND = "down";

    private String currentCommand;

    public DockerComposeDown ( String currentCommand ) {
        this.currentCommand = currentCommand + " " + COMMAND;
    }

    @Override
    public String command () {
        return currentCommand;
    }
}
