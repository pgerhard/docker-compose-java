package com.inventiosystems.docker;

import com.inventiosystems.docker_compose.DockerComposeClient;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Tests to verify command strings created by docker compose client.
 */
public class DockerComposeTest {

    @Test
    public void verifyDockerComposeVersion () {
        final String dockerComposeVersionCommand = new DockerComposeClient ().version ();

        assertThat ( dockerComposeVersionCommand, is ( "docker-compose --version" ) );
    }

    @Test
    public void verifyDockerComposeHelp () {
        final String dockerComposeHelpCommand = new DockerComposeClient ().help ();

        assertThat ( dockerComposeHelpCommand, is ( "docker-compose --help" ) );
    }

    @Test
    public void verifyDockerComposeUpWithFileInCompatibility () {
        final String dockerComposeFileUpWithCompatibilityAndDetach = new DockerComposeClient ()
                .file ( "docker-compose-infrastructure.yaml" )
                .compatibility ()
                .up ()
                .detach ()
                .toString ();

        assertThat ( dockerComposeFileUpWithCompatibilityAndDetach,
                is ( "docker-compose --file docker-compose-infrastructure.yaml --compatibility up --detach" ) );
    }

    public void verifyDockerComposeUpWithServiceInCompatibility () {
        final String dockerComposeUpWithServicesInCompatibilityAndDetach = new DockerComposeClient ()
                .compatibility ()
                .up ()
                .detach ()
                .withServices ( "mssql-server", "maildev", "ipa-icsp-config-server" )
                .toString ();

        assertThat ( dockerComposeUpWithServicesInCompatibilityAndDetach,
                is ( "docker-compose --compatibility up -d mssql-server maildev ipa-icsp-config-server" ) );
    }

    @Test
    public void verifyDockerComposeStartWithServiceInCompatibility () {
        final String dockerComposeStartWithServiceInCompatibilityAndDetach = new DockerComposeClient ()
                .compatibility ()
                .start ()
                .withServices ( "mssql-server", "maildev", "ipa-icsp-config-server" )
                .toString ();

        assertThat ( dockerComposeStartWithServiceInCompatibilityAndDetach,
                is ( "docker-compose --compatibility start mssql-server maildev ipa-icsp-config-server" ));
    }
}
