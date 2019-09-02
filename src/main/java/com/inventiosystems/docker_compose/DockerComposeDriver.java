package com.inventiosystems.docker_compose;

import java.io.File;
import java.io.IOException;

/**
 * Wrapper around the docker-compose client responsible for handling the actual execution of the created command.
 *
 * TODO:
 */
public class DockerComposeDriver {

    private File workingDirectory;

    /**
     * Initialize the driver in the default working directory
     *
     * @return a docker compose client to work with for this driver instance
     */
    public DockerComposeDriver initialize () {
        return this;
    }

    /**
     * Initialize the docker compose driver and set the provided working directory as the working directory.
     *
     * @param directory to set as the working directory
     *
     * @return a docker compose client to work with for this driver instance
     */
    public DockerComposeDriver initialize ( File directory ) {
        this.workingDirectory = directory;
        return initialize ();
    }

    /**
     * Execute this driver with the command provided by the given command provider.
     *
     * @param commandProvider for the command to executed
     *
     * @return exit code for the command
     */
    public int execute ( HasCommand commandProvider ) {
        final String command = commandProvider.command ();
        return execute ( command );
    }

    /**
     * Execute this driver with the given command. The command must be the final runnable command. No modifications will be made on this command. If
     * modifications are required an object implementing {@link HasCommand} must be used instead.
     *
     * @param command to execute
     *
     * @return exit code for the command
     */
    public int execute ( String command ) {
        final ProcessBuilder processBuilder;
        if ( isWindows () ) {
            processBuilder = new ProcessBuilder ( "cmd.exe", "/c" );
        } else {
            processBuilder = new ProcessBuilder ( "bash", "-c" );
        }

        if ( this.workingDirectory != null ) {
            processBuilder.directory ( workingDirectory );
        }

        System.out.println ( command );
        processBuilder.command ().add ( command );
        processBuilder.inheritIO ();
        try {
            final Process process = processBuilder.start ();
            return process.waitFor ();
        } catch ( IOException | InterruptedException ex ) {
            throw new RuntimeException ( ex );
        }
    }

    private static boolean isWindows () {
        return System.getProperty ( "os.name" ).toLowerCase ().startsWith ( "windows" );
    }
}
