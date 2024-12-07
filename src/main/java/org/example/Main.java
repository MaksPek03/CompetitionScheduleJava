package org.example;

import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args){
        Schedule schedule_1 = new Schedule.ScheduleBuilder()
                .setId("schedule_1")
                .setName("My running Calendar")
                .setIsPublic(true)
                .build();
        Schedule schedule_2 = new Schedule.ScheduleBuilder()
                .setId("schedule_1")
                .setName("Other competition calendar")
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
                .setDate("2024-06-20")
                .setDistance(24.500)
                .build();
        Run run3 = new Run.RunBuilder()
                .setId("run_3")
                .setName("Parkun")
                .setDistance(5.00)
                .setDate("2024-05-09")
                .build();
        Run run4 = new Run.RunBuilder()
                .setId("run_4")
                .setName("Mountain bike race")
                .setDistance(80.00)
                .setDate("2024-07-12")
                .build();

        schedule_1.addRun(run1);
        schedule_2.addRun(run2);
        schedule_1.addRun(run3);
        schedule_2.addRun(run4);

        List<Schedule> schedules = new ArrayList<>();
        schedules.add(schedule_1);
        schedules.add(schedule_2);

        schedules.forEach(schedule -> {
                System.out.println("Schedule" + schedule);
                schedule.getRuns().forEach(run -> System.out.println(" Run" + run));
        }
        );

        Set<Run> allRuns_set = schedules.stream()
                .flatMap(schedule -> schedule.getRuns().stream())
                .filter(run -> run.getDistance() > 10.00)
                .sorted(Comparator.comparing(Run::getDistance))
                .collect(Collectors.toSet());

        allRuns_set.forEach(System.out::println);




//        List<Run> allRuns = schedules.stream()
//                .flatMap(schedule -> schedule.getRuns().stream())
//                .filter(run -> run.getDistance() > 10.00)
//                .sorted(Comparator.comparing(Run::getDistance))
//                .toList();
//
//        allRuns
//            .forEach(System.out::println);

        List<RunDTO> sortedRunDtos = schedules.stream()
                .flatMap(schedule -> schedule.getRuns().stream())
                .map(Run::toDTO)
                .sorted()
                .toList();

        sortedRunDtos.forEach(System.out::println);


    }
}
