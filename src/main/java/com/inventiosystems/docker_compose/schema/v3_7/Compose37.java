package com.inventiosystems.docker_compose.schema.v3_7;

import java.util.Map;

public class Compose37 {

    private String version;

    private Map<String, Service37> services;

    public String getVersion () {
        return version;
    }

    public void setVersion ( String version ) {
        this.version = version;
    }

    public Map<String, Service37> getServices () {
        return services;
    }

    public void setServices ( Map<String, Service37> services ) {
        this.services = services;
    }
}
