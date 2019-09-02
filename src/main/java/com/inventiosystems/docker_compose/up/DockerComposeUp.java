package com.inventiosystems.docker_compose.up;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Object providing DSL for docker-compose up command.
 */
public class DockerComposeUp {

    private static final String COMMAND = "up";

    private static final String DETACH = " --detach";

    private final Set<String> services = new HashSet<> ();

    private String currentCommand;

    public DockerComposeUp ( String existingCommand ) {
        currentCommand = existingCommand + " " + COMMAND;
    }

    public DockerComposeUp detach () {
        currentCommand = currentCommand + DETACH;
        return this;
    }

    public DockerComposeUp withService ( String serviceName ) {
        services.add ( serviceName );
        return this;
    }

    public DockerComposeUp withServices ( String... serviceNames ) {
        services.addAll ( new HashSet<> ( Arrays.asList ( serviceNames ) ) );
        return this;
    }

    public DockerComposeUp withServices (Set<String> serviceNames) {
        services.addAll ( serviceNames );
        return this;
    }

    @Override
    public String toString () {
        return currentCommand;
    }


}
