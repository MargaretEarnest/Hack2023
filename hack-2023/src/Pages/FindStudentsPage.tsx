import * as React from 'react';
import {Badge, Button, IconButton, Paper, Snackbar} from "@mui/material";
import ArrowCircleRightIcon from '@mui/icons-material/ArrowCircleRight';
import ArrowCircleLeftIcon from '@mui/icons-material/ArrowCircleLeft';

function getStringWithConjunction(a: string[], conj: string) {
    return a.slice(0, -1).join(', ') + (a.length > 1 ? " " + conj + " " : '') + a.slice(-1);
}

function FindStudentsPage() {
    function slide(containerId: string, direction: string) {
        let container = document.getElementById(containerId);
        if (container) {
            container.scrollLeft += direction == "left" ? -50 : 50;
        }
    }

    const [successfullyConnected, setSuccessfullyConnected] = React.useState(false);

    let human = {
        email: "molly7312000@gmail.com",
        prefix: "Mr.",
        fName: "Margaret",
        lName: "Earnest",
        suffix: "the fifth",
        status: 1,
        majors: ["Computer Science", "Philosophy"],
        yearOfGraduation: 2023,
        gpa: 3.93,
        classes: ["MQP", "CS 7834", "MU 4999"]
    };

    let appHumans = [human, human, human, human, human, human, human, human, human, human, human, human];
    let recHumans = [human, human, human, human, human, human, human, human, human, human, human, human];

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
            <div style={{display: "flex", width: "100%"}}>
                <div style={{
                    height: "calc(100vh - 88px)",
                    width: "500px",
                    minWidth: "500px",
                    backgroundColor: "#B8D5ED",
                    margin: "10px",
                    display: "flex",
                    flexDirection: "column",
                    alignItems: "center"
                }}>
                    <h3 style={{textAlign: "center", fontSize: "30px", marginBottom: 10}}>Your Jobs</h3>
                    <Button variant={"contained"} style={{width: "300px", height: "100px"}}>Add Job</Button><br/>
                    <div id={"scrollBarDiv"} style={{overflowY: "scroll", width: "100%"}}><br/>
                        {jobs.map(j =>
                            <Paper className="noselect profJob" style={{
                                position: "relative",
                                width: "80%",
                                height: "100px",
                                backgroundColor: "#FFF7D6",
                                display: "flex",
                                placeContent: "center",
                                margin: "auto",
                                alignItems: "center",
                                cursor: "pointer"
                            }}>
                                <Badge badgeContent={4} color="primary"  showZero style={{position: "absolute", right: 0, top: 0}}/>
                                <span style={{fontSize: "30px"}}>{j.title}</span><br/>
                            </Paper>
                        )}
                    </div>
                </div>
                <div style={{maxHeight: "100%", overflow: 'auto', width: "98%", margin: "10px"}}>
                    <h1 >BoGL Web</h1>
                    <span style={{marginLeft: "65px", fontSize: "20px", fontWeight: "bold"}}>Applicants:</span>
                    <div style={{marginTop: "10px", display: "flex", height: "35%", alignItems: "center"}}>
                        <IconButton onClick={() => slide("appContainer", 'left')}>
                            <ArrowCircleLeftIcon sx={{ fontSize: 50 }} />
                        </IconButton>
                        <div id="appContainer" style={{padding: "5px", position: "relative", backgroundColor: "lightgray", overflow: "auto", whiteSpace: "nowrap", overflowX: "scroll", overflowY: "clip", width: "85%", height: "100%", margin: "auto"}}>
                            {appHumans.map(app => <div className={"studentList"} style={{position: "relative", overflowY: "auto", padding: "10px", fontSize: "12px", backgroundColor: "#FFF7D6", whiteSpace: "normal", display: "inline-block", margin: "5px", width: "170px", height: "90%"}}>
                                <p><b>{app.prefix + " " + app.fName + " " + app.lName + " " + app.suffix}</b></p>
                                <p>{["Undergraduate", "Grad student", "Doctoral student", "Postdoc"][app.status] + ", grad year " + app.yearOfGraduation}</p>
                                <p>{"Majoring in " + getStringWithConjunction(app.majors, "and")}</p>
                                <p>{"Has taken " + getStringWithConjunction(app.classes, "and")}</p>
                                <p>Percent match: 90%</p>
                                <Button color={"success"} variant={"contained"} style={{borderRadius: 0, width: "50%", position: "absolute", bottom: 0, left: 0}}>Accept</Button>
                                <Button color={"error"} variant={"contained"} style={{borderRadius: 0, width: "50%", position: "absolute", bottom: 0, right: 0}}>Reject</Button>
                            </div>)}
                        </div>
                        <IconButton onClick={() => slide("appContainer", 'right')}>
                            <ArrowCircleRightIcon sx={{ fontSize: 50 }}/>
                        </IconButton>
                    </div><br/>
                    <span style={{marginLeft: "65px", fontSize: "20px", fontWeight: "bold"}}>Recommended students:</span>
                    <div style={{marginTop: "10px", display: "flex", height: "35%", alignItems: "center"}}>
                        <IconButton onClick={() => slide("appContainer", 'left')}>
                            <ArrowCircleLeftIcon sx={{ fontSize: 50 }} />
                        </IconButton>
                        <div id="appContainer" style={{padding: "5px", position: "relative", backgroundColor: "lightgray", overflow: "auto", whiteSpace: "nowrap", overflowX: "scroll", overflowY: "clip", width: "85%", height: "100%", margin: "auto"}}>
                            {appHumans.map(app => <div className={"studentList"} style={{position: "relative", overflowY: "auto", padding: "10px", fontSize: "12px", backgroundColor: "#FFF7D6", whiteSpace: "normal", display: "inline-block", margin: "5px", width: "170px", height: "90%"}}>
                                <p><b>{app.prefix + " " + app.fName + " " + app.lName + " " + app.suffix}</b></p>
                                <p>{["Undergraduate", "Grad student", "Doctoral student", "Postdoc"][app.status] + ", grad year " + app.yearOfGraduation}</p>
                                <p>{"Majoring in " + getStringWithConjunction(app.majors, "and")}</p>
                                <p>{"Has taken " + getStringWithConjunction(app.classes, "and")}</p>
                                <p>Percent match: 90%</p>
                                <Button color={"secondary"} variant={"contained"} style={{borderRadius: 0, width: "100%", position: "absolute", bottom: 0, left: 0}}>Connect</Button>
                            </div>)}
                        </div>
                        <IconButton onClick={() => slide("appContainer", 'right')}>
                            <ArrowCircleRightIcon sx={{ fontSize: 50 }}/>
                        </IconButton>
                    </div>
                    <Snackbar
                        open={successfullyConnected}
                        autoHideDuration={3000}
                        onClose={() => {
                            setSuccessfullyConnected(false)
                        }}
                        message="The student has been sent a request to collaborate on this project"
                    />
                </div>
            </div>
        </div>
    );
}

export default FindStudentsPage;