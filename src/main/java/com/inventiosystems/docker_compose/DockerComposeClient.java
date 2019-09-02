package com.inventiosystems.docker_compose;

import com.inventiosystems.docker_compose.options.DockerComposeOptions;
import com.inventiosystems.docker_compose.start.DockerComposeStart;
import com.inventiosystems.docker_compose.stop.DockerComposeStop;
import com.inventiosystems.docker_compose.up.DockerComposeUp;

/**
 * Object providing a DSL for docker-compose.
 */
public class DockerComposeClient {

    private static final String COMMAND = "docker-compose";

    private static final String FILE = " --file %s";

    private static final String HELP = " --help";

    private static final String VERSION = " --version";

    private String currentCommand;

    public DockerComposeClient () {
        currentCommand = COMMAND;
    }

    public DockerComposeClient file ( String fileName ) {
        currentCommand = currentCommand + String.format ( FILE, fileName );
        return this;
    }

    // Terminal methods

    public String help () {
        return COMMAND + HELP;
    }

    public String version () {
        return COMMAND + VERSION;
    }

    // Options

    public DockerComposeOptions projectName ( String projectName ) {
        return new DockerComposeOptions ( currentCommand ).projectName ( projectName );
    }

    public DockerComposeOptions verbose () {
        return new DockerComposeOptions ( currentCommand ).verbose ();
    }

    public DockerComposeOptions compatibility () {
        return new DockerComposeOptions ( currentCommand ).compatibility ();
    }

    // Commands

    public DockerComposeStart start () {
        return new DockerComposeStart ( currentCommand );
    }

    public DockerComposeStop stop () {
        return new DockerComposeStop ( currentCommand );
    }

    public DockerComposeUp up () {
        return new DockerComposeUp ( currentCommand );
    }


    @Override
    public String toString () {
        return currentCommand;
    }
}
