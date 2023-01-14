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

function SignupPage() {
    const [accountType, setAccountType] = React.useState("");
    const [status, setStatus] = React.useState(0);
    const [gradYear, setGradYear] = React.useState(2023);

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
                        <TextField className="accountForm" id="email" label="Email" variant="outlined"/><br/>
                        <TextField className="accountForm" id="fName" label="First Name" variant="outlined"/><br/>
                        <TextField className="accountForm" id="lName" label="Last Name" variant="outlined"/><br/>
                        {accountType == "professor" ?
                            <>
                                <TextField className="accountForm" id="prefix" label="Prefix" variant="outlined"/><br/>
                            </>
                            : <></>
                        }
                        <TextField className="accountForm" id="suffix" label="Suffix" variant="outlined"/><br/>
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
                        <AutocompleteMultiselect data={DataLists.collegeMajors} name={"Majors"}/><br/>
                        {accountType == "student" &&
                            <>
                                <InputLabel className="accountForm" id="yearLabel" variant="standard">Graduation
                                    Year</InputLabel>
                                <Select className="accountForm"
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
                                <TextField className="accountForm" style={{marginLeft: "20%"}} type="number" id="gpa"
                                           label="GPA" variant="outlined"/><br/>
                                <AutocompleteMultiselect data={DataLists.courses} name={"Classes"}/><br/>
                            </>
                        }
                        <Button variant={"contained"} style={{width: "160px", marginLeft: "30%"}}>Create
                            Account</Button>
                    </div>
                </>
            }
        </div>
    );
}

export default SignupPage;