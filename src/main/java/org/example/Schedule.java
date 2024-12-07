package org.example;

import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import java.io.Serializable;

public class Schedule implements Comparable<Schedule>, Serializable {
    private static final long serialVersionUID = 1L;
    private final String id;
    private final String name;
    private final boolean isPublic;
    private final Set<Run> runs;

    public Schedule(ScheduleBuilder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.isPublic = builder.isPublic;
        this.runs = new HashSet<>();
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public boolean isPublic() {
        return isPublic;
    }
    public Set<Run> getRuns() {
        return runs;
    }

    public void addRun(Run run) {
        runs.add(run);
        run.setSchedule(this);
    }

    @Override
    public String toString(){
        return "Schedule{id='" + id + "', name = ' " + name + "', if is Public " + isPublic + "', runs = " + runs.size() + "}";

    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return Objects.equals(id, schedule.id);
    }
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
    @Override
    public int compareTo(Schedule other){
        return this.name.compareTo(other.name);
    }

    public static class ScheduleBuilder{
        private String id;
        private String name;
        private boolean isPublic;

        public ScheduleBuilder setId(String id){
            this.id = id;
            return this;
        }
        public ScheduleBuilder setName(String name){
            this.name = name;
            return this;
        }
        public ScheduleBuilder setIsPublic(boolean isPublic){
            this.isPublic = isPublic;
            return this;
        }
        public Schedule build(){
            return new Schedule(this);
        }
    }

}