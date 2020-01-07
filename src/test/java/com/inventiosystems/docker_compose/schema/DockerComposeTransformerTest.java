package com.inventiosystems.docker_compose.schema;

import com.inventiosystems.docker_compose.schema.common.Compose;
import com.inventiosystems.docker_compose.schema.common.Service;
import com.inventiosystems.docker_compose.schema.v3_7.Compose37;
import com.inventiosystems.docker_compose.schema.v3_7.DockerComposeParser;
import com.inventiosystems.docker_compose.schema.v3_7.DockerComposeTransformer;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class DockerComposeTransformerTest {

    @Test
    public void verifyDockerComposeTransformer () throws URISyntaxException {

        final ClassLoader contextClassLoader = Thread.currentThread ().getContextClassLoader ();
        final URL resource = contextClassLoader.getResource ( "dockerComposeParserTest/verifySchemaVersion37Test/docker-compose.yaml" );
        final Path path = Paths.get ( resource.toURI () );
        final File composeFile = path.toFile ();

        assertThat ( composeFile.exists (), is ( true ) );

        final DockerComposeParser dockerComposeParser = new DockerComposeParser ();
        final Compose37 parsedCompose37 = dockerComposeParser.parse ( composeFile );
        final Compose compose = DockerComposeTransformer.transform ( parsedCompose37 );

        assertThat ( compose.getVersion ().isPresent (), is ( true ) );
        compose.getVersion ().ifPresent ( version -> assertThat ( version, is ( "3.7" ) ) );

        assertThat ( compose.getServices ().isPresent (), is ( true ) );
        final Map<String, Service> serviceNameToServiceMap = compose.getServices ().get ();
        assertThat ( serviceNameToServiceMap.get ( "hello-world" ), is ( notNullValue () ) );

        final Service service = serviceNameToServiceMap.get ( "hello-world" );
        assertThat ( service.getImage ().isPresent (), is ( true ) );
        assertThat ( service.getPorts ().isPresent (), is ( true ) );
        assertThat ( service.getDependsOn ().isPresent (), is ( true ) );

    }
}
