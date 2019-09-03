package com.inventiosystems.docker_compose;

import com.inventiosystems.docker_compose.down.DockerComposeDown;
import com.inventiosystems.docker_compose.options.DockerComposeOptions;
import com.inventiosystems.docker_compose.rm.DockerComposeRemove;
import com.inventiosystems.docker_compose.start.DockerComposeStart;
import com.inventiosystems.docker_compose.stop.DockerComposeStop;
import com.inventiosystems.docker_compose.up.DockerComposeUp;

/**
 * Object providing a DSL for docker-compose.
 */
public class DockerComposeClient implements HasCommand {

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

    /**
     * Run the docker-compose down command. The returned object provides a fluent API to specify the same information as could be provided on the cli.
     *
     * <pre>
     *     docker-compose down [options]
     * </pre>
     *
     * @return the docker compose down object
     */
    public DockerComposeDown down () {
        return new DockerComposeDown ( currentCommand );
    }

    /**
     * Run the docker-compose rm command. The returned object provides a fluent API to specify the same information as could be provided on the cli.
     *
     * <pre>
     *     docker-compose rm [options] [SERVICE...]
     * </pre>
     *
     * @return the docker compose remove object
     */
    public DockerComposeRemove remove () {
        return new DockerComposeRemove ( currentCommand );
    }

    /**
     * Run the docker-compose start command. The returned object provides a fluent API to build up the command as if it was executed on the cli.
     *
     * <pre>
     *     docker-compose start [SERVICE...]
     * </pre>
     *
     * @return the docker compose start object
     */
    public DockerComposeStart start () {
        return new DockerComposeStart ( currentCommand );
    }

    /**
     * Run the docker-compose stop command. The returned object provides a fluent API to build up the command as if it was executed on the cli.
     *
     * <pre>
     *     docker-compose stop [options] [SERVICE...]
     * </pre>
     *
     * @return the docker compose stop object
     */
    public DockerComposeStop stop () {
        return new DockerComposeStop ( currentCommand );
    }

    /**
     * Run the docker-compose up command. The returned object provides a fluent API to build up the command as if it was executed on the cli.
     *
     * <pre>
     *     docker-compose up [options] [--scale SERVICE=NUM...] [SERVICE...]
     * </pre>
     *
     * @return the docker compose up object
     */
    public DockerComposeUp up () {
        return new DockerComposeUp ( currentCommand );
    }


    @Override
    public String command () {
        return currentCommand;
    }
}
