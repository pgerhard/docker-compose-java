package com.inventiosystems.docker_compose.schema.common;

import java.util.Optional;

public class Image {

    private final String id;

    private final String version;

    public Image ( String id, String version ) {
        this.id = id;
        this.version = version;
    }

    public Optional<String> getId () {
        return Optional.ofNullable ( id );
    }

    public Optional<String> getVersion () {
        return Optional.ofNullable ( version );
    }
}
