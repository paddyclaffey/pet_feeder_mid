package com.claffey.petminder.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduleConfig {

    private int min;

    public ScheduleConfig() {
        min = 0;
    }

    @Scheduled(fixedRate = 60000) // Run every minute
    public void executeTask() {
        min++;
        System.out.println("executeTask: " + min);
        // Perform the database query and action here
        // You can inject the necessary dependencies such as a repository or service

        // Example:
        // Query the database
//        List<Entity> results = yourRepository.findAll();
//
//        // Perform the action based on the results
//        for (Entity entity : results) {
//            // Perform your action here
//        }
    }
}
