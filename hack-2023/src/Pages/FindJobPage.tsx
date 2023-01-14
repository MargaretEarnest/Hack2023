import * as React from 'react';
import {
    Box, Checkbox, FormControlLabel,
    InputLabel,
    List,
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
            <div style={{display: "flex"}}>
                {/*majors - multi-select search*/}
                {/*type - multi-select (no search)*/}
                {/*department - multi-select search*/}
                {/*location - multi-select search*/}
                {/*hours - double ended slider*/}
                {/*numStudents - double ended slider*/}
                {/*federalWorkStudy - checkbox*/}
                <div>
                    <InputLabel id="statusLabel" variant="standard">Status</InputLabel>
                    <Select
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
                    </Select><br/>
                    <AutocompleteMultiselect data={DataLists.collegeMajors} name={"Majors"}/><br/>
                    <AutocompleteMultiselect data={DataLists.departments} name={"Departments"}/><br/>
                    <AutocompleteMultiselect data={DataLists.locations} name={"Locations"}/><br/>
                    <Box sx={{ width: 300 }}>
                        <InputLabel id="hoursPerWeekLabel" variant="standard">Hours per Week</InputLabel>
                        <Slider
                            id={"hoursPerWeek"}
                            getAriaLabel={() => 'Hours per Week'}
                            value={hours}
                            onChange={(event: Event, newValue: number | number[]) => {
                                setHours(newValue as number[]);
                            }}
                            valueLabelDisplay="auto"
                        />
                    </Box>
                    <Box sx={{ width: 300 }}>
                        <InputLabel id="teamSizeLabel" variant="standard">Team Size</InputLabel>
                        <Slider
                            id={"teamSize"}
                            getAriaLabel={() => 'Team Size'}
                            value={teamSize}
                            onChange={(event: Event, newValue: number | number[]) => {
                                setTeamSize(newValue as number[]);
                            }}
                            valueLabelDisplay="auto"
                        />
                    </Box>
                    <FormControlLabel control={<Checkbox defaultChecked />} label="Label" />
                </div>
                <Paper style={{maxHeight: 200, overflow: 'auto'}}>
                    <List>

                    </List>
                </Paper>
            </div>
        </div>
    );
}

export default FindJobPage;