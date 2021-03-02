package com.inventiosystems.docker_compose.schema.v3_7;

import java.util.List;

/**
 * A docker-compose 3.7 service definition.
 */
public class Service37 {

    private String image;

    private List<String> ports;

    private List<String> depends_on;

    public String getImage () {
        return image;
    }

    public void setImage ( String image ) {
        this.image = image;
    }

    public List<String> getPorts () {
        return ports;
    }

    public void setPorts ( List<String> ports ) {
        this.ports = ports;
    }

    public List<String> getDepends_on () {
        return depends_on;
    }

    public void setDepends_on ( List<String> depends_on ) {
        this.depends_on = depends_on;
    }
}
