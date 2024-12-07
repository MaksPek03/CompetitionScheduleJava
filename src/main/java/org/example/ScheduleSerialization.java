package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleSerialization {
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

        // Serialize the collection of schedules to a binary file
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("schedules.dat"))) {
            oos.writeObject(schedules);
            System.out.println("Schedules have been serialized to schedules.dat.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Deserialize the collection of schedules from the binary file
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("schedules.dat"))) {
            List<Schedule> deserializedSchedules = (List<Schedule>) ois.readObject();
            System.out.println("\nDeserialized Schedules and their Runs:");
            for (Schedule schedule : deserializedSchedules) {
                System.out.println(schedule);
                schedule.getRuns().forEach(run -> System.out.println("  " + run));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

