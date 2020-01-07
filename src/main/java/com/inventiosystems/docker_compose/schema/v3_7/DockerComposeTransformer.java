package com.inventiosystems.docker_compose.schema.v3_7;

import com.inventiosystems.docker_compose.schema.common.Image;
import com.inventiosystems.docker_compose.schema.common.Port;
import com.inventiosystems.docker_compose.schema.common.Service;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DockerComposeTransformer {

    public static com.inventiosystems.docker_compose.schema.common.Compose transform ( Compose37 composeV37 ) {
        final Map<String, Service> services = transformComposeV37Service ( composeV37.getServices () );
        return new com.inventiosystems.docker_compose.schema.common.Compose ( composeV37.getVersion (), services );
    }

    private static Map<String, Service> transformComposeV37Service ( Map<String, Service37> services ) {
        return services.entrySet ().stream ()
            .map ( entry -> {
                final Service37 service37 = entry.getValue ();
                final Image image = extractImage ( service37.getImage () );
                final List<Port> ports = extractPorts ( service37.getPorts () );
                final List<String> dependsOn = extractDependsOn ( service37.getDepends_on () );
                final Service right = new Service ( image, ports, dependsOn );
                return ImmutablePair.of ( entry.getKey (), right );
            } )
            .collect (
                HashMap::new,
                ( map, pair ) -> map.put ( pair.getKey (), pair.getValue () ),
                Map::putAll
            );
    }

    private static List<String> extractDependsOn ( List<String> depends_on ) {
        if ( CollectionUtils.isEmpty ( depends_on ) ) {
            return Collections.emptyList ();
        }

        return new ArrayList<> ( depends_on );
    }

    private static List<Port> extractPorts ( List<String> ports ) {
        if ( CollectionUtils.isEmpty ( ports ) ) {
            return Collections.emptyList ();
        }

        return ports.stream ()
            .map ( DockerComposeTransformer::extractPort )
            .collect ( Collectors.toList () );
    }

    private static Port extractPort ( String portString ) {
        if ( StringUtils.isBlank ( portString ) ) {
            throw new IllegalArgumentException ( "The port string may not be blank" );
        }

        final List<String> strings = Arrays.asList ( portString.split ( ":" ) );
        if ( CollectionUtils.isEmpty ( strings ) ) {
            throw new IllegalArgumentException ( "Could not extract any ports" );
        }
        String source = strings.get ( 0 );
        String target = strings.size () > 1 ? strings.get ( 1 ) : StringUtils.EMPTY;
        return new Port ( source, target );
    }

    private static Image extractImage ( String image ) {
        final List<String> strings = Arrays.asList ( image.split ( ":" ) );
        if ( CollectionUtils.isEmpty ( strings ) ) {
            throw new IllegalStateException ( "Could not extract version" );
        }

        return new Image ( strings.get ( 0 ), strings.get ( 1 ) );
    }
}
