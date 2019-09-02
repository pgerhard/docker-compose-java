package com.inventiosystems.docker;

import com.inventiosystems.docker_compose.DockerComposeClient;
import com.inventiosystems.docker_compose.DockerComposeDriver;
import com.inventiosystems.docker_compose.rm.DockerComposeRemove;
import com.inventiosystems.docker_compose.start.DockerComposeStart;
import com.inventiosystems.docker_compose.up.DockerComposeUp;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Unit test for the {@link DockerComposeDriver}.
 */
public class DockerComposeDriverTest {

    /**
     * Verify that docker-compose up works with a basic file.
     *
     * @throws URISyntaxException not expected
     */
    @Test
    public void verifyDockerComposeDriverTest () throws URISyntaxException {
        final ClassLoader contextClassLoader = Thread.currentThread ().getContextClassLoader ();
        final URL resource = contextClassLoader.getResource ( "docker-compose-driver-test-files/verifyDockerComposeDriverTest" );
        final Path path = Paths.get ( resource.toURI () );
        final File workingDirectory = path.toFile ();

        this.cleanDirectory ( workingDirectory );

        final DockerComposeUp dockerComposeStartServices = new DockerComposeClient ()
            .up ()
            .withServices ( "hello-world" );
        final int exitCode = new DockerComposeDriver ().initialize ( workingDirectory ).execute ( dockerComposeStartServices );

        assertThat ( exitCode, is ( 0 ) );

        this.cleanDirectory ( workingDirectory );
    }

    private void cleanDirectory ( File targetDirectory ) {
        final String remove = new DockerComposeClient ().remove ().force ();
        new DockerComposeDriver ().initialize ( targetDirectory ).execute ( remove );
    }
}
