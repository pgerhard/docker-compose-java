package com.inventiosystems.docker_compose.common;

import com.inventiosystems.docker_compose.schema.common.Compose;
import com.inventiosystems.docker_compose.schema.common.Service;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedAcyclicGraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ComposeGraphHelper {

    public static Map<Integer, Map<String, Service>> calculateServiceGraph ( Compose compose ) {
        if ( Objects.isNull ( compose ) ) {
            return new HashMap<> ();
        }

        if ( !compose.getServices ().isPresent () ) {
            return new HashMap<> ();
        }

        final Map<String, Service> stringServiceMap = compose.getServices ().get ();

        final DirectedAcyclicGraph<String, DefaultEdge> serviceGraph = new DirectedAcyclicGraph<> ( DefaultEdge.class );

        stringServiceMap.keySet ().forEach ( serviceGraph::addVertex );
        stringServiceMap.entrySet ().forEach ( service -> {
            service.getValue ().getDependsOn ().ifPresent ( strings -> {
                strings.forEach ( dependentsName -> serviceGraph.addEdge ( service.getKey (), dependentsName ) );
            } );
        } );

        int groupCount = 0;
        final Map<Integer, Map<String, Service>> orderedServiceMap = new HashMap<> ();

        final Iterator<String> iterator = serviceGraph.iterator ();
        while ( iterator.hasNext () ) {
            final String serviceName = iterator.next ();
            final Service currentService = stringServiceMap.get ( serviceName );
            Map<String, Service> services = orderedServiceMap.get ( groupCount );
            if ( currentService.getDependsOn ().isPresent () && CollectionUtils.isNotEmpty ( currentService.getDependsOn ().get () ) ) {
                if ( MapUtils.isEmpty ( services ) ) {
                    services = new HashMap<> ();
                }
                if ( Objects.nonNull ( services.get ( serviceName ) ) ) {
                    groupCount++;
                    services = new HashMap<> (  );
                }
                services.put (serviceName, currentService );
                orderedServiceMap.put ( groupCount, services );
            } else {
                if ( MapUtils.isEmpty ( services ) ) {
                    services = new HashMap<> ();
                }
                services.put (serviceName, currentService );
                orderedServiceMap.put ( groupCount, services );
            }
        }

        return orderedServiceMap;
    }

}
