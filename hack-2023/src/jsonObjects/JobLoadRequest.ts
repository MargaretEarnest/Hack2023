import {MinMax} from "./MinMax";

export class JobLoadRequest{
    private hours:MinMax;
    private teamSize:MinMax;
    private allLocations:String[]

    constructor(hours:MinMax, teamSize:MinMax, allLocations:String[]) {
        this.hours = hours;
        this.teamSize = teamSize;
        this.allLocations = allLocations;
    }

}