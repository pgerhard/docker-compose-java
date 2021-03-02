package com.inventiosystems.docker_compose.schema.v3_7;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Parse a file into a {@link Compose37} object.
 */
public class DockerComposeParser {

    public Compose37 parse ( File dockerComposeFile ) {
        try {
            Representer representer = new Representer();
            representer.getPropertyUtils().setSkipMissingProperties(true);

            final Yaml yaml = new Yaml ( new Constructor ( Compose37.class ), representer );
            return yaml.load ( new FileInputStream ( dockerComposeFile ) );
        } catch ( FileNotFoundException e ) {
            throw new IllegalStateException ( "Could not parse YAML", e );
        }
    }

}
