package com.travelport.io.healthapp.additions;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class HealthCheck implements HealthIndicator {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMddyyyy'T'HH:mm:ss.SSSZZZZ");
    @Override
    public Health health() {
        int errorCode = check(); // perform some specific health check
        if (errorCode != 0) {
            return Health.down()
                    .withDetail("Error Code", errorCode).build();
        }
        System.out.println("***************************** Date:"+dateFormat.format(new Date()));
        System.out.println("System is up health was call and HEALTH_APP_PORT: \n"+System.getenv("HEALTH_APP_PORT")+"\n PID: \\n"+System.getenv("PID"));
        System.out.println("_______________________________");
        return Health.up().withDetail("DateNtime", ""+dateFormat.format(new Date())).build();
    }

    public int check() {
        // Our logic to check health
        return 0;
    }
}
