package com.inventiosystems.docker_compose.options;

import com.inventiosystems.docker_compose.stop.DockerComposeStop;
import com.inventiosystems.docker_compose.up.DockerComposeUp;

public class DockerComposeOptions {

    private static final String COMPATIBILITY = " --compatibility";

    private static final String PROJECT_NAME = "--project-name %s";

    private static final String VERBOSE = " --verbose";

    private String currentCommand;

    public DockerComposeOptions ( String existingCommand ) {
        this.currentCommand = existingCommand;
    }

    public DockerComposeOptions projectName ( String projectName ) {
        currentCommand = currentCommand + String.format ( PROJECT_NAME, projectName );
        return this;
    }

    public DockerComposeOptions verbose () {
        currentCommand = currentCommand + VERBOSE;
        return this;
    }

    public DockerComposeOptions compatibility () {
        currentCommand = currentCommand + COMPATIBILITY;
        return this;
    }

    // Commands

    public DockerComposeStop stop () {
        return new DockerComposeStop ( currentCommand );
    }

    public DockerComposeUp up () {
        return new DockerComposeUp ( currentCommand );
    }
}
