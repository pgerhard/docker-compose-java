package com.inventiosystems.docker_compose.schema.common;

import java.util.Map;
import java.util.Optional;

public class Compose {

    private final String version;

    private final Map<String, Service> services;

    public Compose ( String version, Map<String, Service> services ) {
        this.version = version;
        this.services = services;
    }

    public Optional<String> getVersion () {
        return Optional.ofNullable ( version );
    }

    public Optional<Map<String, Service>> getServices () {
        return Optional.ofNullable ( services );
    }
}
