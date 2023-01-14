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

function SignupPage() {
    const [accountType, setAccountType] = React.useState("");
    const [status, setStatus] = React.useState(0);
    const [gradYear, setGradYear] = React.useState(2023);
    const collegeMajors = ["Actuarial Mathematics", "Aerospace Engineering", "Applied Mathematics", "Applied Physics", "Applied Statistics", "Architectural Engineering", "Astrophysics", "Biochemistry", "Bioinformatics & Computational Biology", "Biology", "Biology & Biotechnology", "Biomanufacturing", "Biomedical Engineering", "Biomedical Engineering (MEng)", "Bioscience Management", "Business", "Business Administration", "Business Analytics", "Chemical Engineering", "Chemical Engineering (Professional MS)", "Chemistry", "Civil Engineering", "Civil Engineering (MEng)", "Community Climate Adaptation", "Computational Media", "Computer Science", "Computer Science (MCS)", "Construction Project Management", "Creative Writing", "Cybersecurity", "Data Science", "Drama/Theatre", "Economics", "Electrical & Computer Engineering", "Electrical & Computer Engineering (MEng)", "English", "Entrepreneurship", "Environmental & Sustainability Studies", "Environmental Engineering", "Executive PhD", "Financial Technology", "Fire Protection Engineering", "Foreign Language", "Global Public Health", "History", "Humanities & Arts", "Industrial Engineering", "Industrial Mathematics", "Information Security Management", "Information Technology", "Innovation with User Experience (IUX)", "Interactive Media & Game Design (MFA)", "Interactive Media & Game Development", "Interactive Media & Game Development (BA)", "International & Global Studies", "Latin American & Caribbean Studies", "Law & Technology", "Learning Sciences & Technologies", "Liberal Arts & Engineering", "Life Sciences Management", "Management", "Management Engineering", "Management Information Systems", "Manufacturing Engineering", "Materials", "Materials Process Engineering", "Materials Science & Engineering", "Mathematical Sciences", "Mathematics", "Mathematics for Educators (MME)", "Mathematics for Educators (MMED)", "Mechanical Engineering", "Mechanical Engineering for Technical Leaders (METL)", "Media Arts", "Medicinal Plant Chemistry", "Music", "Nanoscience", "Neuroscience", "Nuclear Science & Engineering", "Operations and Supply Chain Analytics (MS)", "Philosophy and Religion", "Physics", "Physics for Educators", "Power Systems Engineering", "Power Systems Engineering (MEng)", "Power Systems Management", "Psychology", "Robotics Engineering", "Science and Engineering for Development", "Science and Technology for Innovation in Global Development", "Society,  Technology & Policy", "Statistics", "Supply Chain Analytics", "Supply Chain Essentials", "Sustainability Engineering", "System Dynamics", "System Dynamics & Innovation Management", "Systems Engineering", "Systems Engineering Leadership", "Systems Modeling", "Writing & Rhetoric", "Writing (Professional)"];
    const courses = ["PE 1009", "PE 1006", "PE 1099", "ECON 1110", "ECON 1120", "IQP", "MQP", "ID2050", "EN 1000", "EN 1100", "GEO 1000", "ES 1310", "CS 1102", "CS 2103", "CS 2303", "CS 2201", "CS 3733", "CS 3043", "CS 2011", "CS 2013", "CS 4241", "CS 4233", "CS 4432", "CS 4513", "CS 4342", "CS 509", "CS 528", "CS 5084", "MIS 585", "CS 525", "MA 1021", "MA 1022", "MA 1023", "MA 1024", "MA 2201", "MA 2621", "MA 2611", "CS 4123", "CS 4533", "CS 4120", "CS 4536", "PH 1110", "PH 1120", "PH 1130", "RBE 1001", "RBE 2002", "RBE 3002", "ECE 2029", "ES 2503"];

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
                        <AutocompleteMultiselect data={collegeMajors} name={"Majors"}/><br/>
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
                                <AutocompleteMultiselect data={courses} name={"Classes"}/><br/>
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