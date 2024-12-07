package org.example;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class ParallelStreamWithCustomThreadPool {
    public static void main(String[] args) {
        Schedule schedule_1 = new Schedule.ScheduleBuilder()
                .setId("schedule_1")
                .setName("My Running Calendar")
                .setIsPublic(true)
                .build();

        Schedule schedule_2 = new Schedule.ScheduleBuilder()
                .setId("schedule_2")
                .setName("Competition Calendar")
                .setIsPublic(true)
                .build();

        Run run1 = new Run.RunBuilder()
                .setId("run_1")
                .setName("Gdansk Marathon")
                .setDistance(42.195)
                .setDate("2024-04-06")
                .build();

        Run run2 = new Run.RunBuilder()
                .setId("run_2")
                .setName("Triathlon")
                .setDistance(24.5)
                .setDate("2024-06-20")
                .build();

        schedule_1.addRun(run1);
        schedule_2.addRun(run2);

        List<Schedule> schedules = new ArrayList<>();
        schedules.add(schedule_1);
        schedules.add(schedule_2);

        int poolSize = 4;
        ForkJoinPool customThreadPool = new ForkJoinPool(poolSize);


        try {
            customThreadPool.submit(() -> schedules.parallelStream()
                    .forEach(schedule -> {
                        try {
                            Thread.sleep(2000);
                            System.out.println("Processing Schedule: " + schedule.getName());
                            schedule.getRuns().forEach(run -> {
                                try {
                                    Thread.sleep(5000);
                                    System.out.println("  " + run);
                                } catch (InterruptedException e) {
                                    Thread.currentThread().interrupt();
                                }
                            });
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    })).get();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            customThreadPool.shutdown();
            try {
                if (!customThreadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                    customThreadPool.shutdownNow();
                }
            } catch (InterruptedException e) {
                customThreadPool.shutdownNow();
            }
        }
    }
}
