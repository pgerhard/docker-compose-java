package com.inventiosystems.docker_compose.start;

import com.inventiosystems.docker_compose.HasCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Fluent API for docker-compose start.
 */
public class DockerComposeStart {

    private static final String COMMAND = "start";

    private static final String HELP = "--help";

    private String currentCommand;

    public DockerComposeStart ( String existingCommand ) {
        this.currentCommand = existingCommand + " " + COMMAND;
    }

    public DockerComposeStartServices withService ( String serviceName ) {
        return new DockerComposeStartServices ( currentCommand )
                .withServices ( serviceName );
    }

    public DockerComposeStartServices withServices ( String... serviceNames ) {
        return new DockerComposeStartServices ( currentCommand )
                .withServices ( serviceNames );
    }

    public DockerComposeStartServices withServices ( Set<String> serviceNames ) {
        return new DockerComposeStartServices ( currentCommand )
                .withServices ( serviceNames );
    }

    public String help () {
        currentCommand = currentCommand + " " + HELP;
        return currentCommand;
    }

    public class DockerComposeStartServices implements HasCommand {

        private final String currentCommand;

        private final Set<String> services;

        public DockerComposeStartServices ( String currentCommand ) {
            this.currentCommand = currentCommand;
            this.services = new HashSet<> ();
        }

        public DockerComposeStartServices withServices ( String... services ) {
            this.services.addAll ( new HashSet<> ( Arrays.asList ( services ) ) );
            return this;
        }

        public DockerComposeStartServices withServices ( Set<String> services ) {
            this.services.addAll ( services );
            return this;
        }

        public String command () {
            String finalCommand = this.currentCommand;
            if ( !services.isEmpty () ) {
                for (String serviceName : services) {
                    finalCommand = finalCommand + " " + serviceName;
                }
            }
            return finalCommand;
        }
    }
}
