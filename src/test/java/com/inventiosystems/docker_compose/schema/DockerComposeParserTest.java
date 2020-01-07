package com.inventiosystems.docker_compose.schema;

import com.inventiosystems.docker_compose.schema.v3_7.Compose37;
import com.inventiosystems.docker_compose.schema.v3_7.DockerComposeParser;
import com.inventiosystems.docker_compose.schema.v3_7.Service37;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

public class DockerComposeParserTest {

    @Test
    public void verifySchemaVersion37Test () throws URISyntaxException {
        final ClassLoader contextClassLoader = Thread.currentThread ().getContextClassLoader ();
        final URL resource = contextClassLoader.getResource ( "dockerComposeParserTest/verifySchemaVersion37Test/docker-compose.yaml" );
        final Path path = Paths.get ( resource.toURI () );
        final File composeFile = path.toFile ();

        assertThat ( composeFile.exists (), is ( true ) );

        final DockerComposeParser dockerComposeParser = new DockerComposeParser ();
        final Compose37 parsedCompose37 = dockerComposeParser.parse ( composeFile );

        assertThat ( parsedCompose37.getVersion (), is ( "3.7" ) );
        assertThat ( parsedCompose37.getServices ().size (), is ( 1 ) );

        final Service37 service37 = parsedCompose37.getServices ().get ( "hello-world" );
        assertThat ( service37, is ( notNullValue () ) );
        assertThat ( service37.getImage (), is ( "hello-world:latest" ) );

        assertThat ( service37.getDepends_on (), is ( not ( emptyIterable () ) ) );
        assertThat ( service37.getDepends_on ().size (), is ( 1 ) );
        assertThat ( service37.getDepends_on (), containsInAnyOrder ( "another-service" ) );
    }

}
