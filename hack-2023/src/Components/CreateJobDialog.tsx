import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import CloseIcon from "@mui/icons-material/Close";
import DialogActions from "@mui/material/DialogActions";
import {Checkbox, FormControlLabel, InputLabel, MenuItem, Select, SelectChangeEvent} from "@mui/material";
import AutocompleteMultiselect from "./AutocompleteMultiselect";
import {DataLists} from "../DataLists";
import {MinMax} from "../jsonObjects/MinMax";
import {JobListRequest} from "../jsonObjects/JobListRequest";
import {BackendRequest} from "../jsonObjects/BackendRequest";

export default function CreateJobDialog(props: { currentUser: string, open: boolean, email: string, handleClose: () => void }) {
    const [status, setStatus] = React.useState(0);
    const [majors, setMajors] = React.useState([]);
    const [requirements, setRequirements] = React.useState([]);
    const [department, setDepartment] = React.useState<string>("");

    return (
        <Dialog open={props.open} fullWidth maxWidth="lg" onClose={props.handleClose}>
            <DialogActions>
                <Button onClick={props.handleClose} style={{minWidth: 0}}><CloseIcon/></Button>
            </DialogActions>
            <DialogTitle paddingBottom={"0px !important"} fontSize={"30px !important"}
                         align={"center"} marginTop={"-30px"}>Create New Job</DialogTitle>
            <br/>
            <DialogContent style={{textAlign: "center"}}>
                <div style={{display: "flex", width: "100%", flexDirection: "row", flexWrap: "wrap"}}>
                    <TextField id="title" label="Title" variant="outlined"/><br/>
                    <div style={{marginTop: "-22px"}}>
                        <InputLabel id="depLabel" variant="standard">Department</InputLabel>
                        <Select
                            labelId="depLabel"
                            id="department"
                            itemType={"string"}
                            value={department.toString()}
                            label="Department"
                            variant="outlined"
                            style={{width: "210px"}}
                            onChange={(event: SelectChangeEvent) => setDepartment(event.target.value)}
                        >
                            {DataLists.departments.map(d => <MenuItem value={d}>{d}</MenuItem>)}
                        </Select>
                    </div>
                    <TextField
                        className={"formSpacing"}
                        id="desc"
                        label="Description"
                        multiline
                        rows={1}
                        defaultValue=""
                    /><br/>
                    <div style={{marginTop: "-22px"}}>
                        <InputLabel id="statusLabel" variant="standard">Status</InputLabel>
                        <Select
                            labelId="statusLabel"
                            id="status"
                            itemType={"number"}
                            value={status.toString()}
                            label="Desired Student Program"
                            variant="outlined"
                            style={{width: "210px"}}
                            onChange={(event: SelectChangeEvent) => setStatus(parseInt(event.target.value))}
                        >
                            <MenuItem value={0}>Undergrad</MenuItem>
                            <MenuItem value={1}>Grad Student</MenuItem>
                            <MenuItem value={2}>Doctoral Student</MenuItem>
                            <MenuItem value={3}>Postdoc</MenuItem>
                        </Select>
                    </div>
                    <AutocompleteMultiselect setValue={setMajors} width="210px" marginLeft="0"
                                             data={DataLists.collegeMajors} name={"Majors"}/><br/>
                    <TextField className={"formSpacing"} id="email" label="Email" variant="outlined"/><br/>
                    <TextField className={"formSpacing"} id="gpa" label="Desired GPA" type="number" variant="outlined"/><br/>
                    <TextField className={"formSpacing"} id="employees" label="Employees" variant="outlined"/><br/><br/>
                    <TextField className={"formSpacing"} id="year" label="Desired Class Year" type="number"
                               variant="outlined"/><br/>
                    <AutocompleteMultiselect setValue={setRequirements} width="210px" marginLeft="0"
                                             data={DataLists.courses} name={"Required Courses"}/><br/>
                    <TextField className={"formSpacing"} id="phone" label="Phone Number" variant="outlined"/><br/>
                    <TextField className={"formSpacing"} id="contact" label="Job Contact" variant="outlined"/><br/>
                    <TextField className={"formSpacing"} id="hours" label="Hours per Week" type="number"
                               variant="outlined"/><br/>
                    <TextField className={"formSpacing"} id="teamSize" label="Team Size" type="number"
                               variant="outlined"/><br/>
                    <FormControlLabel style={{marginLeft: "6px", marginBottom: "10px"}} control={<Checkbox id={"workStudy"} defaultChecked/>} label="Federal Work Study Only"/>
                    <TextField className={"formSpacing"} id="location" label="Location" variant="outlined"/><br/>
                </div>
                <p>Please enter each employee associated with the project with a comma between entries, ex. "John
                    Doe, Jane Doe"</p>
                <br/>
                <Button variant="contained" style={{width: "150px", marginBottom: "10px"}} onClick={() => {
                    console.log({title: getById("title"), department: department, desc: getById("desc"), status: status,
                        majors: majors, email: props.email, gpa: getById("gpa"), employees: getById("employees").split(", "),
                        gradYear: getById("year"), requirements: requirements, phone: getById("phone"), contact: getById("contact"),
                        hours: parseInt(getById("hours")), teamSize: parseInt(getById("teamSize")), workStudy: !document.getElementById("workStudy")?.parentElement?.classList.contains("Mui-checked"),
                    location: getById("location")});

                    let request = new BackendRequest("CreateJob", JSON.stringify({title: getById("title"), department: department, desc: getById("desc"), status: status,
                        majors: majors, email: props.email, gpa: getById("gpa"), employees: getById("employees").split(", "),
                        gradYear: getById("year"), requirements: requirements, phone: getById("phone"), contact: getById("contact"),
                        hours: parseInt(getById("hours")), teamSize: parseInt(getById("teamSize")), workStudy: !document.getElementById("workStudy")?.parentElement?.classList.contains("Mui-checked"),
                        location: getById("location")}));

                    let websocket = new WebSocket("ws://71.233.252.195:8129");
                    websocket.onopen = () => {
                        console.log(request);
                        websocket.send(JSON.stringify(request));
                    };
                    websocket.onmessage = (event) => {
                        console.log(event);
                        websocket.close();
                    };
                }}>Create Job</Button><br/>
            </DialogContent>
        </Dialog>
    );
}

export function getById(id: string) {
    return (document.getElementById(id) as HTMLInputElement)?.value;
}