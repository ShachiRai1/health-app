package com.travelport.io.healthapp.additions;

import org.springframework.boot.actuate.endpoint.annotation.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Endpoint(id="active-cluster")
public class ActiveClusterEndpoint {

    private final Map<String, User> users = new HashMap();

    ActiveClusterEndpoint() {
        this.users.put("env", new User(""+System.getenv()));
        this.users.put("HOSTNAME", new User(""+System.getProperty("HOSTNAME")));
        this.users.put("OPENSHIFT_BUILD_COMMIT", new User(""+System.getProperty("OPENSHIFT_BUILD_COMMIT")));
        this.users.put("HEALTH_APP_PORT", new User(""+System.getProperty("HEALTH_APP_PORT")));
        this.users.put("KUBERNETES_PORT_53_UDP_ADDR", new User(""+System.getProperty("KUBERNETES_PORT_53_UDP_ADDR")));
        this.users.put("clusterId", new User(""+System.getProperty("clusterId")));
    }

    @ReadOperation
    public List getAll() {
        return new ArrayList(this.users.values());
    }

    @ReadOperation
    public User getActiveUser(@Selector String user) {
        return this.users.get(user);
    }

    @WriteOperation
    public void updateActiveUser(@Selector String name, String user) {
        this.users.put(name, new User(user));
    }

    @DeleteOperation
    public void deleteActiveUser(@Selector String name) {
        this.users.remove(name);
    }

    public static class User {
        private String name;

        User(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
