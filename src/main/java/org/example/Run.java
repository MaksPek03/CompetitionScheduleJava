package org.example;

import java.util.Objects;
import java.io.Serializable;

public class Run implements Comparable<Run>, Serializable {
    private static final long serialVersionUID = 1L;
    private final String id;
    private final String name;
    private final String date;
    private final double distance;
    private Schedule schedule;

    public Run(RunBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.date = builder.date;
        this.distance = builder.distance;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public double getDistance(){
        return distance;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public RunDTO toDTO() {
        return new RunDTO(name, date, distance, schedule != null ? schedule.getName() : "None");
    }

    @Override
    public String toString() {
        return "Run{id='" + id + "', name='" + name + "', date='" + date + "', distance " + distance + "', scheduleName='" +
                (schedule != null ? schedule.getName() : "None") + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Run run = (Run) o;
        return Objects.equals(id, run.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Run other){
        return this.id.compareTo(other.id);
    }

    public static class RunBuilder{
        private String id;
        private String name;
        private String date;
        private double distance;

        public RunBuilder setId(String id){
            this.id = id;
            return this;
        }
        public RunBuilder setName(String name){
            this.name = name;
            return this;
        }
        public RunBuilder setDate(String date){
            this.date = date;
            return this;
        }
        public RunBuilder setDistance(double distance){
            this.distance = distance;
            return this;
        }
        public Run build(){
            return new Run(this);
        }
    }
}

