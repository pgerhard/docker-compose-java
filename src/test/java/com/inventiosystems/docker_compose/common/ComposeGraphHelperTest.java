package com.inventiosystems.docker_compose.common;

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
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

public class ComposeGraphHelperTest {

    private static final String DIRECTORY_NAME = "composeGraphHelperTest";
    private static final String COMPOSE_FILE_NAME = "docker-compose.yaml";

    @Test
    public void verifyOneLayerGraphWithOneVertex () {
        final String name = "verifyOneLayerGraphWithOneVertex";
        final Compose compose = loadCompose ( name );
        final Map<Integer, Map<String, Service>> oneLayerServiceGraph = ComposeGraphHelper.calculateServiceGraph ( compose );
        assertThat ( compose,  notNullValue () );

        assertThat ( oneLayerServiceGraph, notNullValue () );
        assertThat ( oneLayerServiceGraph.size (), is ( 1 ) );
        final Map<String, Service> layerOneServices = oneLayerServiceGraph.get ( 0 );
        assertThat ( layerOneServices, notNullValue () );
        assertThat ( layerOneServices.size (), is ( 1 ) );
        assertThat ( layerOneServices.keySet (), containsInAnyOrder ( "hello-world" ) );
    }

    @Test
    public void verifyOneLayerGraphWithTwoVertices () {
        final String name = "verifyOneLayerGraphWithTwoVertices";
        final Compose compose = loadCompose ( name );
        final Map<Integer, Map<String, Service>> oneLayerServiceGraph = ComposeGraphHelper.calculateServiceGraph ( compose );
        assertThat ( compose,  notNullValue () );

        assertThat ( oneLayerServiceGraph, notNullValue () );
        assertThat ( oneLayerServiceGraph.size (), is ( 1 ) );
        final Map<String, Service> layerOneServices = oneLayerServiceGraph.get ( 0 );
        assertThat ( layerOneServices, notNullValue () );
        assertThat ( layerOneServices.size (), is ( 2 ) );
        assertThat ( layerOneServices.keySet (), containsInAnyOrder ( "hello-world-one", "hello-world-two" ) );
    }

    private Compose loadCompose ( String name ) {
        try {
            final ClassLoader contextClassLoader = Thread.currentThread ().getContextClassLoader ();
            final URL resource = contextClassLoader.getResource ( DIRECTORY_NAME + "/" + name + "/" + COMPOSE_FILE_NAME );
            final Path path = Paths.get ( resource.toURI () );
            final File composeFile = path.toFile ();
            final DockerComposeParser dockerComposeParser = new DockerComposeParser ();
            final Compose37 parsedCompose37 = dockerComposeParser.parse ( composeFile );
            return DockerComposeTransformer.transform ( parsedCompose37 );
        }catch ( URISyntaxException e) {
            throw new RuntimeException ( e );
        }
    }
}
