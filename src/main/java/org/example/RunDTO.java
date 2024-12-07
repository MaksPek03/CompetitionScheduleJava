package org.example;

public class RunDTO implements Comparable<RunDTO> {
    private final String name;
    private final String date;
    private final double distance;
    private final String scheduleName;

    public RunDTO(String name, String date, double distance, String scheduleName){
        this.name = name;
        this.date = date;
        this.distance = distance;
        this.scheduleName = scheduleName;

    }
    public String getName(){
        return name;
    }
    public String getDate(){
        return date;
    }
    public double getDistance(){
        return distance;
    }
    public String getScheduleName(){
        return scheduleName;
    }
    @Override
    public String toString() {
        return "RunDTO{ name='" + name + "', date='" + date + "', distance " + distance + "', scheduleName='" + scheduleName + "'}";
    }
    @Override
    public int compareTo(RunDTO other) {
        int distanceComparison = Double.compare(this.distance, other.distance);
        if (distanceComparison != 0) {
            return distanceComparison;
        }

        // If distances are the same, compare by name
        return this.name.compareTo(other.name);
    }


}
