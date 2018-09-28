package com.travelport.io.healthapp.additions;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck implements HealthIndicator {

    @Override
    public Health health() {
        int errorCode = check(); // perform some specific health check
        if (errorCode != 0) {
            return Health.down()
                    .withDetail("Error Code", errorCode).build();
        }
        System.out.println("*****************************");
        System.out.println("system is up by Travelport health is call and System.getenv(): \\n"+System.getenv());
        System.out.println("_______________________________");
        return Health.up().build();
    }

    public int check() {
        // Our logic to check health
        return 0;
    }
}
