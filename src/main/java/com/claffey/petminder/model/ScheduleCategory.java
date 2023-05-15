package com.claffey.petminder.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public enum ScheduleCategory {
    FEEDING,
    MEDICATION,
    EXERCISE,
    OTHER;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NotificationMessage {
        private String message;

    }
}
