package com.example.CardProductService.Model;

import org.springframework.stereotype.Component;

@Component
public class Utility {

    private static long lastTimestamp = System.currentTimeMillis();
    private static long counter = 0;

    public static synchronized String generateId() {
        long currentTime = System.currentTimeMillis();

        if (currentTime == lastTimestamp) {
            counter++;
        } else {
            lastTimestamp = currentTime;
            counter = 0;
        }

        return String.valueOf(currentTime * 1000 + counter);
    }
}