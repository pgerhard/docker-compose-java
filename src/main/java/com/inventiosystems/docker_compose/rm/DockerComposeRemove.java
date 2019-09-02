package com.inventiosystems.docker_compose.rm;

import com.inventiosystems.docker_compose.HasCommand;

/**
 * Wrapper object for docker-compose rm.
 */
public class DockerComposeRemove implements HasCommand {

    private static final String COMMAND = "rm";

    private static final String FORCE = "--force";

    private String currentCommand;

    /**
     * Create a new instance of the command.
     *
     * @param currentCommand the docker-compose command that has been build up so far.
     */
    public DockerComposeRemove ( String currentCommand ) {
        this.currentCommand = currentCommand + " " + COMMAND;
    }

    /**
     * Set the force option.
     *
     * @return the object wrapping the command with the force option applied.
     */
    public DockerComposeRemove force () {
        this.currentCommand = currentCommand + " " + FORCE;
        return this;
    }

    @Override
    public String command () {
        return currentCommand;
    }
}
