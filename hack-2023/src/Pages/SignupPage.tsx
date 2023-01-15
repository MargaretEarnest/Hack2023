import * as React from 'react';
import SchoolTwoToneIcon from '@mui/icons-material/SchoolTwoTone';
import BackpackTwoToneIcon from '@mui/icons-material/BackpackTwoTone';
import CircleTwoToneIcon from '@mui/icons-material/CircleTwoTone';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import {
    Button,
    IconButton,
    InputLabel,
    MenuItem,
    Select,
    SelectChangeEvent,
    TextField,
    Typography
} from "@mui/material";
import AutocompleteMultiselect from "../Components/AutocompleteMultiselect";
import {DataLists} from "../DataLists";
import {BackendRequest} from "../jsonObjects/BackendRequest";
import {Student} from "../jsonObjects/Student";
import {CreateStudentRequest} from "../jsonObjects/CreateStudentRequest";
import {Employer} from "../jsonObjects/Employer";
import {CreateEmployerRequest} from "../jsonObjects/CreateEmployerRequest";
import {Login} from "../jsonObjects/Login";

function SignupPage() {
    const [accountType, setAccountType] = React.useState("");
    const [status, setStatus] = React.useState(0);
    const [gradYear, setGradYear] = React.useState(2023);
    const [majors, setMajors] = React.useState([]);
    const [classes, setClasses] = React.useState([]);

    return (
        <div className="backgroundBox">
            {accountType != "" &&
                <IconButton style={{width: "70px", height: "70px", position: "absolute", borderRadius: 0}}
                            onClick={() => {
                                setAccountType("")
                            }}>
                    <ArrowBackIcon color="primary" style={{fontSize: "70px"}}/>
                </IconButton>
            }
            {accountType == "" ?
                <>
                    <h1 id="signUpAs">Sign Up as a...</h1>
                    <div style={{display: "flex", justifyContent: "space-evenly", height: "60%"}}>
                        <div className={"profileTypeHolder"}>
                            <IconButton className={"accountTypeIconButton"} onClick={() => setAccountType("student")}>
                                <CircleTwoToneIcon color="secondary" style={{fontSize: "400px", position: "absolute"}}/>
                                <BackpackTwoToneIcon color="primary" style={{fontSize: "200px", position: "absolute"}}/>
                                <Typography color={"primary"} fontWeight={"900"} fontSize={"70px"}
                                            style={{position: "absolute", bottom: "-80px"}}>Student</Typography>
                            </IconButton>
                        </div>
                        <div className={"profileTypeHolder"}>
                            <IconButton className={"accountTypeIconButton"} onClick={() => setAccountType("professor")}>
                                <CircleTwoToneIcon color="secondary" style={{fontSize: "400px", position: "absolute"}}/>
                                <SchoolTwoToneIcon color="primary" style={{fontSize: "200px", position: "absolute"}}/>
                                <Typography color={"primary"} fontWeight={"900"} fontSize={"70px"}
                                            style={{position: "absolute", bottom: "-80px"}}>Professor</Typography>
                            </IconButton>
                        </div>
                    </div>
                </>
                : <>
                    <h1>{accountType == "student" ? "Student" : "Professor"} Sign Up</h1><br/>
                    <div style={{
                        display: "flex", flexDirection: "column", margin: "0 20%", flexWrap:
                            "wrap", height: "70%"
                    }}>
                        <TextField className="" id="email" label="Email" variant="outlined"/><br/>
                        <TextField className="" id="fName" label="First Name" variant="outlined"/><br/>
                        <TextField className="" id="lName" label="Last Name" variant="outlined"/><br/>
                        {accountType == "professor" ?
                            <>
                                <TextField className="" id="prefix" label="Prefix" variant="outlined"/><br/>
                            </>
                            : <></>
                        }
                        <TextField className="" id="suffix" label="Suffix" variant="outlined"/><br/>
                        {accountType == "student" &&
                            <>
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
                            </>
                        }
                        <AutocompleteMultiselect setValue={setMajors} width="300px" marginLeft="10%" data={DataLists.collegeMajors} name={"Majors"}/><br/>
                        {accountType == "student" &&
                            <>
                                <InputLabel style={{marginLeft: "10%"}} className="" id="yearLabel" variant="standard">Graduation
                                    Year</InputLabel>
                                <Select className=""
                                        style={{marginLeft: "10%"}}
                                        labelId="yearLabel"
                                        id="graduationYear"
                                        itemType={"number"}
                                        value={gradYear.toString()}
                                        label="Graduation Year"
                                        variant="outlined"
                                        onChange={(event: SelectChangeEvent) => setGradYear(parseInt(event.target.value))}
                                >
                                    {[2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, 2032, 2033].map(y =>
                                        <MenuItem value={y}>{y}</MenuItem>)
                                    }
                                </Select><br/>
                                <TextField className="" style={{marginLeft: "10%"}} type="number" id="gpa"
                                           label="GPA" variant="outlined"/><br/>
                                <AutocompleteMultiselect setValue={setClasses} width="300px" marginLeft="10%" data={DataLists.courses} name={"Classes"}/><br/>
                            </>
                        }
                        <TextField className="" style={{marginLeft: "10%"}} type="password" id="pass1"
                                   label="Password" variant="outlined"/><br/>
                        <TextField className="" style={{marginLeft: "10%"}} type="password" id="pass2"
                                   label="Confirm Password" variant="outlined"/><br/>
                        <Button variant={"contained"} style={{width: "160px", marginLeft: "30%"}} onClick={() => {
                            let request: BackendRequest;
                            if (accountType === "student") {
                                //Create a student
                                let data = "";
                                if(getById("pass1") === getById("pass2")) {
                                    let student = new Student(getById("email"), getById("prefix"), getById("fName"), getById("lName"), getById("suffix"), status, majors, gradYear, parseFloat(getById("gpa")), classes);
                                    let createStudentReq = new CreateStudentRequest(student, getById("pass1"));
                                    data = JSON.stringify(createStudentReq);
                                } else {
                                    alert("Passwords do not match")
                                }
                                request = new BackendRequest("CreateStudent", data);
                            } else {
                                //Create an employer
                                let data = "";
                                if(getById("pass1") === getById("pass2")){
                                    let student = new Employer(getById("email"), getById("prefix"), getById("fname"), getById("lname"), getById("suffix"), status, majors);
                                    let createEmployerReq = new CreateEmployerRequest(student, getById("pass1"));
                                    data = JSON.stringify(createEmployerReq);
                                }else{
                                    alert("Passwords do not match")
                                }
                                request = new BackendRequest("CreateEmployer", data);
                            }
                            let websocket = new WebSocket("ws://localhost:8129");
                            websocket.onopen = () => {
                                console.log(request);
                                websocket.send(JSON.stringify(request));
                            };
                            websocket.onmessage = (event) => {
                                console.log(event);
                                websocket.close();
                            };
                        }}>Create Account</Button>
                    </div>
                </>
            }
        </div>
    );
}

export function getById(id: string) {
    return (document.getElementById(id) as HTMLInputElement)?.value;
}

export default SignupPage;