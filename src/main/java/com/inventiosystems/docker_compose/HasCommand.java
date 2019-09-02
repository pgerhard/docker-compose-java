package com.inventiosystems.docker_compose;

/**
 * Class implementing must be able to provide a command line command.
 */
public interface HasCommand {

    /**
     * The current command as that is defined by this object. This command must be executable in a command line.
     * @return the command as a string.
     */
    String command ();
}
