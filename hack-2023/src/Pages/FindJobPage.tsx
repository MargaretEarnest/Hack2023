import * as React from 'react';
import {
    Box,
    Button,
    Checkbox,
    FormControlLabel,
    InputLabel,
    MenuItem, Paper,
    Select,
    SelectChangeEvent,
    Slider,
    Snackbar,
    ToggleButton,
    ToggleButtonGroup
} from "@mui/material";
import AutocompleteMultiselect from "../Components/AutocompleteMultiselect";
import {DataLists} from "../DataLists";

function getStringWithConjunction(a: string[], conj: string) {
    return a.slice(0, -1).join(', ') + (a.length > 1 ? ", " + conj + " " : '') + a.slice(-1);
}

function FindJobPage() {
    const [filterSelections, setFilterSelections] = React.useState<string[]>([]);
    const [status, setStatus] = React.useState(0);
    const [hours, setHours] = React.useState<number[]>([0, 1000000]);
    const [teamSize, setTeamSize] = React.useState<number[]>([0, 1000000]);
    const [majors, setMajors] = React.useState([]);
    const [departments, setDepartments] = React.useState([]);
    const [locations, setLocations] = React.useState([]);
    const [successfullyApplied, setSuccessfullyApplied] = React.useState(false);

    let job = {
        id: 0,
        creators: "Pradeep and Dave",
        type: 0,
        majors: ["Computer Science", "Mechanical Engineering"],
        title: "BoGL Web",
        department: "Mechanical Engineering",
        location: "WPI",
        numStudents: 4,
        hours: 15,
        email: "meearnest@wpi.edu",
        federalWorkStudy: false,
        desc: "Help ME students make bond graphs by translating this Windows application to a web based application",
        gpa: 3.0,
        classYear: 2023,
        phone: "804-304-2212",
        contact: "Pradeep",
        employees: ["Professor Dave", "Professor Pradeep"]
    };

    let jobs = [job, job, job, job, job, job, job, job, job, job];

    return (
        <div style={{display: "flex", flexDirection: "column", alignItems: "center"}}>
            <h2 style={{marginBottom: "10px"}}>I'm Looking For a...</h2>
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
            </ToggleButtonGroup><br/>
            <div style={{display: "flex", width: "100%"}}>
                <div style={{width: "250px", minWidth: "250px", backgroundColor: "#B8D5ED", margin: "10px"}}>
                    <h3 style={{textAlign: "center", fontSize: "25px", marginBottom: 10}}>Job Filters</h3>
                    <Button style={{marginLeft: "45px"}} variant={"contained"}>Refresh Results</Button><br/><br/>
                    <InputLabel className="searchForJobFilters" id="statusLabel" variant="standard">Status</InputLabel>
                    <Select
                        style={{width: "190px"}}
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
                    <AutocompleteMultiselect setValue={setMajors} width="190px" data={DataLists.collegeMajors}
                                             marginLeft="10%" name={"Majors"}/><br/>
                    <AutocompleteMultiselect setValue={setDepartments} width="190px" data={DataLists.departments}
                                             marginLeft="10%" name={"Departments"}/><br/>
                    <AutocompleteMultiselect setValue={setLocations} width="190px" data={DataLists.locations}
                                             marginLeft="10%" name={"Locations"}/><br/>
                    <Box className="searchForJobFilters" sx={{width: 190}}>
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
                    <Box className="searchForJobFilters" sx={{width: 190}}>
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
                    <FormControlLabel style={{marginLeft: "6px", marginBottom: "10px"}}
                                      control={<Checkbox defaultChecked/>} label="Federal Work Study Only"/>
                </div>
                <div style={{maxHeight: "100%", overflow: 'auto', width: "98%", margin: "10px"}}>
                    <Snackbar
                        open={successfullyApplied}
                        autoHideDuration={3000}
                        onClose={() => {
                            setSuccessfullyApplied(false)
                        }}
                        message="Successfully Applied"
                    />
                    {jobs.map(j =>
                        <Paper style={{
                            position: "relative",
                            width: "auto",
                            height: "auto",
                            backgroundColor: "#FFF7D6",
                            marginBottom: "10px",
                            padding: "10px 130px 10px 15px"
                        }}>
                            <span style={{fontSize: "30px", fontWeight: "bold"}}>{j.title}</span><br/>
                            <em>{j.department} project lead by {getStringWithConjunction(j.employees, "and")}</em>
                            <p>{j.desc}</p>
                            <div style={{display: "flex"}}>
                                <div>
                                    <b>Student
                                        program: </b>{["Undergraduate", "Grad student", "Doctoral student", "Postdoc"][j.type]}<br/>
                                    <b>Desired majors: </b>{getStringWithConjunction(j.majors, "and/or")}<br/>
                                    <b>Minimum GPA: </b>{(Math.round(j.gpa * 100) / 100).toFixed(2)}<br/>
                                </div>
                                <div style={{marginLeft: "40px"}}>
                                    <b>Expected class years: </b>{j.classYear}<br/>
                                    <b>Weekly time commitment: </b>{j.hours} hours<br/>
                                    <b>Federal Work Study: </b>{j.federalWorkStudy ? "Required" : "Not required"}<br/>
                                </div>
                            </div>
                            <Button variant={"contained"} style={{
                                width: "80px",
                                height: "40px",
                                position: "absolute",
                                top: "calc(50% - 20px)",
                                right: 30
                            }}
                            onClick={() => {
                                setSuccessfullyApplied(true)
                            }}
                            >Apply</Button>
                        </Paper>
                    )}
                </div>
            </div>
        </div>
    );
}

export default FindJobPage;