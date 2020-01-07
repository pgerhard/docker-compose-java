package com.inventiosystems.docker_compose.schema.common;

import java.util.Optional;

public class Port {

    private final String source;

    private final String target;

    public Port ( String source, String target ) {
        this.source = source;
        this.target = target;
    }

    public Optional<String> getSource () {
        return Optional.ofNullable ( source );
    }

    public Optional<String> getTarget () {
        return Optional.ofNullable ( target );
    }
}
