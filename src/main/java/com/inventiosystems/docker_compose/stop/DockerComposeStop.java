package com.inventiosystems.docker_compose.stop;

import com.inventiosystems.docker_compose.HasCommand;
import com.inventiosystems.docker_compose.up.DockerComposeUp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Object providing DSL for docker-compose stop command
 */
public class DockerComposeStop implements HasCommand {

    private static final String COMMAND = "stop";

    private final Set<String> services = new HashSet<> ();

    private String currentCommand;

    public DockerComposeStop ( String existingCommand ) {
        this.currentCommand = existingCommand + " " + COMMAND;
    }

    public DockerComposeStop withService ( String serviceName ) {
        services.add ( serviceName );
        return this;
    }

    public DockerComposeStop withServices ( String... serviceNames ) {
        services.addAll ( new HashSet<> ( Arrays.asList ( serviceNames ) ) );
        return this;
    }

    public DockerComposeStop withServices ( Set<String> serviceNames ) {
        services.addAll ( serviceNames );
        return this;
    }

    @Override
    public String command () {
        String finalCommand = this.currentCommand;
        if ( !this.services.isEmpty () ) {
            for ( String serviceName : this.services ) {
                finalCommand = finalCommand + " " + serviceName;
            }
        }
        return finalCommand;
    }
}


