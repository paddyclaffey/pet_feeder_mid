package com.claffey.petminder.config;

import com.claffey.petminder.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class ScheduleConfig {

    @Autowired
    private WebSocketService webSocketService;
    private int min;

    public ScheduleConfig() {
        min = 0;
    }

    @Scheduled(fixedRate = 60000) // Run every minute
    public void executeTask() {
        min++;
        List<String> usernames = this.webSocketService.getUsers();
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
