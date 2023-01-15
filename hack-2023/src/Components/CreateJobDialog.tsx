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

export default function CreateJobDialog(props: { currentUser: string, open: boolean, handleClose: () => void }) {
    const [status, setStatus] = React.useState(0);
    const [gradYear, setGradYear] = React.useState(2023);
    const [majors, setMajors] = React.useState([]);
    const [classes, setClasses] = React.useState([]);

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
                    <TextField id="department" label="Department" variant="outlined"/><br/>
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
                    <AutocompleteMultiselect setValue={setClasses} width="210px" marginLeft="0"
                                             data={DataLists.courses} name={"Required Courses"}/><br/>
                    <TextField className={"formSpacing"} id="phone" label="Phone Number" variant="outlined"/><br/>
                    <TextField className={"formSpacing"} id="contact" label="Job Contact" variant="outlined"/><br/>
                    <TextField className={"formSpacing"} id="hours" label="Hours per Week" type="number"
                               variant="outlined"/><br/>
                    <TextField className={"formSpacing"} id="teamSize" label="Team Size" type="number"
                               variant="outlined"/><br/>
                    <FormControlLabel style={{marginLeft: "6px", marginBottom: "10px"}} control={<Checkbox defaultChecked/>} label="Federal Work Study Only"/>
                    <TextField className={"formSpacing"} id="location" label="Location" variant="outlined"/><br/>
                </div>
                <p>Please enter each employee associated with the project with a comma between entries, ex. "John
                    Doe, Jane Doe"</p>
                <br/>
                <Button variant="contained" style={{width: "150px", marginBottom: "10px"}} onClick={() => {
                }}>Create Job</Button><br/>
            </DialogContent>
        </Dialog>
    );
}

export function getById(id: string) {
    return (document.getElementById(id) as HTMLInputElement)?.value;
}