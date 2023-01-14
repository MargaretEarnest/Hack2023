import * as React from 'react';
import {
    Box, Checkbox, FormControlLabel,
    InputLabel,
    List, ListItem,
    MenuItem,
    Paper,
    Select,
    SelectChangeEvent, Slider,
    ToggleButton,
    ToggleButtonGroup
} from "@mui/material";
import AutocompleteMultiselect from "../Components/AutocompleteMultiselect";
import {DataLists} from "../DataLists";

function FindJobPage() {
    const [filterSelections, setFilterSelections] = React.useState<string[]>([]);
    const [status, setStatus] = React.useState(0);
    const [hours, setHours] = React.useState<number[]>([0, 1000000]);
    const [teamSize, setTeamSize] = React.useState<number[]>([0, 1000000]);

    return (
        <div style={{display: "flex", flexDirection: "column", alignItems: "center"}}>
            <h2>I'm Looking For a...</h2>
            <ToggleButtonGroup
                color="primary"
                value={filterSelections}
                onChange={(e) => {
                    // @ts-ignore
                    let pressedButton: string = e.target.value;
                    if (filterSelections.includes(pressedButton)) {
                        setFilterSelections(filterSelections.filter(a => a != pressedButton))
                    } else {
                        setFilterSelections(filterSelections.concat([pressedButton]))
                    }
                }}
                aria-label="Filters"
            >
                <ToggleButton value="research">Research Position</ToggleButton>
                <ToggleButton value="ta">Teaching Assistant Position</ToggleButton>
                <ToggleButton value="campus-job">Campus Job</ToggleButton>
            </ToggleButtonGroup>
            <div style={{display: "flex", width: "100%"}}>
                <div style={{width: "300px", backgroundColor: "#B8D5ED", margin: "10px"}}>
                    <h3 style={{textAlign: "center", fontSize: "25px"}}>Search Filters</h3>
                    <InputLabel className="searchForJobFilters" id="statusLabel" variant="standard">Status</InputLabel>
                    <Select
                        className="searchForJobFilters"
                        labelId="statusLabel"
                        id="status"
                        itemType={"number"}
                        value={status.toString()}
                        label="Status"
                        variant="outlined"
                        onChange={(event: SelectChangeEvent) => setStatus(parseInt(event.target.value))}
                    >
                        <MenuItem value={0}>Undergrad</MenuItem>
                        <MenuItem value={1}>Grad Student</MenuItem>
                        <MenuItem value={2}>Doctoral Student</MenuItem>
                        <MenuItem value={3}>Postdoc</MenuItem>
                    </Select><br/><br/>
                    <AutocompleteMultiselect width="190px" data={DataLists.collegeMajors} marginLeft="10%" name={"Majors"}/><br/>
                    <AutocompleteMultiselect width="190px" data={DataLists.departments} marginLeft="10%" name={"Departments"}/><br/>
                    <AutocompleteMultiselect width="190px" data={DataLists.locations} marginLeft="10%" name={"Locations"}/><br/>
                    <Box className="searchForJobFilters" sx={{ width: 190 }}>
                        <InputLabel id="hoursPerWeekLabel" variant="standard">Hours per Week</InputLabel>
                        <Slider
                            id={"hoursPerWeek"}
                            getAriaLabel={() => 'Hours per Week'}
                            value={hours}
                            onChange={(event: Event, newValue: number | number[]) => {
                                setHours(newValue as number[]);
                            }}
                            valueLabelDisplay="auto"
                            step={1}
                            min={0}
                            max={40}
                        />
                    </Box>
                    <Box className="searchForJobFilters" sx={{ width: 190 }}>
                        <InputLabel id="teamSizeLabel" variant="standard">Team Size</InputLabel>
                        <Slider
                            id={"teamSize"}
                            getAriaLabel={() => 'Team Size'}
                            value={teamSize}
                            onChange={(event: Event, newValue: number | number[]) => {
                                setTeamSize(newValue as number[]);
                            }}
                            valueLabelDisplay="auto"
                            step={1}
                            min={1}
                            max={20}
                        />
                    </Box>
                    <FormControlLabel style={{marginLeft: "6px", marginBottom: "10px"}} control={<Checkbox defaultChecked />} label="Federal Work Study Only" />
                </div>
                <div style={{maxHeight: "100%", overflow: 'auto', width: "100%", backgroundColor: "gray", margin: "10px"}}>
                </div>
            </div>
        </div>
    );
}

export default FindJobPage;