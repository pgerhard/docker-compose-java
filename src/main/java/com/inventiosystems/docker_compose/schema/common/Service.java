package com.inventiosystems.docker_compose.schema.common;

import java.util.List;
import java.util.Optional;

/**
 * A service defined in a compose file
 */
public class Service {

    private final Image image;

    private final List<Port> ports;

    private final List<String> dependsOn;

    public Service ( Image image, List<Port> ports, List<String> dependsOn ) {
        this.image = image;
        this.ports = ports;
        this.dependsOn = dependsOn;
    }

    public Optional<Image> getImage () {
        return Optional.ofNullable ( image );
    }

    public Optional<List<Port>> getPorts () {
        return Optional.ofNullable ( ports );
    }

    public Optional<List<String>> getDependsOn () {
        return Optional.ofNullable ( dependsOn );
    }
}
