package jsonObjects;

public class JobLoadRequest {

    private MinMax hours;
    private MinMax teamSize;
    private Object[] allLocations;

    public JobLoadRequest(MinMax hours, MinMax teamSize, Object[] allLocations) {
        this.hours = hours;
        this.teamSize = teamSize;
        this.allLocations = allLocations;
    }

    public MinMax getHours() {
        return hours;
    }

    public void setHours(MinMax hours) {
        this.hours = hours;
    }

    public MinMax getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(MinMax teamSize) {
        this.teamSize = teamSize;
    }

    public Object[] getAllLocations() {
        return allLocations;
    }

    public void setAllLocations(String[] allLocations) {
        this.allLocations = allLocations;
    }
}
