import * as React from 'react';
import {Badge, Button, IconButton, Paper, Snackbar} from "@mui/material";
import CreateJobDialog from "../Components/CreateJobDialog";
import {ArrowCircleLeft, ArrowCircleRight} from "@mui/icons-material";
import {BackendRequest} from "../jsonObjects/BackendRequest";
import {useEffect} from "react";
import {Student} from "../jsonObjects/Student";

function getStringWithConjunction(a: string[], conj: string) {
    if (a) {
        return a.slice(0, -1).join(', ') + (a.length > 1 ? " " + conj + " " : '') + a.slice(-1);
    }
}

function FindStudentsPage(props: {
    email: string, currentUser: string
}) {
    function slide(containerId: string, direction: string) {
        let container = document.getElementById(containerId);
        if (container) {
            container.scrollLeft += direction == "left" ? -50 : 50;
        }
    }

    let appStudentMap: any = {};

    function loadStudents(jobName: string) {
        let request = new BackendRequest("StudentList", JSON.stringify({JobTitle: jobName}))

        let websocket = new WebSocket("ws://71.233.252.195:8129");
        websocket.onopen = () => {
            console.log(request);
            websocket.send(JSON.stringify(request));
        };
        websocket.onmessage = (event) => {
            console.log(event, JSON.parse(event.data).studentList);
            appStudentMap[jobName] = JSON.parse(event.data).studentList;
            websocket.close();
        };
    }

    const [successfullyConnected, setSuccessfullyConnected] = React.useState(false);
    const [acceptStudent, setAcceptStudent] = React.useState(false);
    const [rejectStudent, setRejectStudent] = React.useState(false);
    const [selectedJob, setSelectedJob] = React.useState<any>(null);
    const [appStudents, setAppStudents] = React.useState<Student[]>([]);
    const [jobs, setJobs] = React.useState<string[]>([]);

    useEffect(function() {
        let request = new BackendRequest("GetJobs", "")

        let websocket = new WebSocket("ws://71.233.252.195:8129");
        websocket.onopen = () => {
            console.log(request);
            websocket.send(JSON.stringify(request));
        };
        websocket.onmessage = (event) => {
            console.log(event);
            setJobs(JSON.parse(event.data).filter((j: any) => j != "null"));
            for (const job of JSON.parse(event.data).filter((j: any) => j != "null")) {
                loadStudents(job);
            }
            console.log(jobs);
            websocket.close();
        };
    }, []);

    const [newJobOpen, setNewJobOpen] = React.useState(false);
    const handleNewJobOpen = () => {
        setNewJobOpen(true);
    };
    const handleNewJobClose = () => {
        setNewJobOpen(false);
    };

    return (
        <div style={{display: "flex", flexDirection: "column", alignItems: "center"}}>
            <CreateJobDialog open={newJobOpen} handleClose={handleNewJobClose} currentUser={props.currentUser} email={props.email}/>
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
                    <Button variant={"contained"} style={{width: "300px", height: "100px"}} onClick={() => {
                        handleNewJobOpen()
                    }}>Add Job</Button><br/>
                    <div id={"scrollBarDiv"} style={{overflowY: "scroll", width: "100%"}}><br/>
                        {jobs.map(j =>
                            <Paper className={"noselect profJob "} style={{
                                position: "relative",
                                width: "80%",
                                height: "100px",
                                backgroundColor: "#FFF7D6",
                                display: "flex",
                                placeContent: "center",
                                margin: "auto",
                                alignItems: "center",
                                cursor: "pointer"
                            }} onClick={() => {
                                setSelectedJob(j)
                            }}>
                                <Badge badgeContent={appStudentMap[j] ? appStudentMap[j].length : 0} color="primary" showZero
                                       style={{position: "absolute", right: 0, top: 0}}/>
                                <span style={{fontSize: "30px"}}>{j}</span><br/>
                            </Paper>
                        )}
                    </div>
                </div>
                <div style={{maxHeight: "100%", overflow: 'auto', width: "98%", margin: "10px"}}>
                    {selectedJob && <>
                        <h1>BoGL Web</h1>
                        <span style={{marginLeft: "65px", fontSize: "20px", fontWeight: "bold"}}>Applicants:</span>
                        <div style={{marginTop: "10px", display: "flex", height: "35%", alignItems: "center"}}>
                            <IconButton onClick={() => slide("appContainer", 'left')}>
                                <ArrowCircleLeft sx={{fontSize: 50}}/>
                            </IconButton>
                            <div id="appContainer" style={{
                                padding: "5px",
                                position: "relative",
                                backgroundColor: "lightgray",
                                overflow: "auto",
                                whiteSpace: "nowrap",
                                overflowX: "scroll",
                                overflowY: "clip",
                                width: "85%",
                                height: "100%",
                                margin: "auto"
                            }}>
                                {(appStudentMap[selectedJob] ? appStudentMap[selectedJob] : []).map((app: Student) => <div className={"studentList"} style={{
                                    position: "relative",
                                    overflowY: "auto",
                                    padding: "10px",
                                    fontSize: "12px",
                                    backgroundColor: "#FFF7D6",
                                    whiteSpace: "normal",
                                    display: "inline-block",
                                    margin: "5px",
                                    width: "170px",
                                    height: "90%"
                                }}>
                                    <p><b>{app.prefix + " " + app.fName + " " + app.lName + " " + app.suffix}</b></p>
                                    <p>{["Undergraduate", "Grad student", "Doctoral student", "Postdoc"][app.status] + ", grad year " + app.yearOfGraduation}</p>
                                    <p>{"Majoring in " + app.majors}</p>
                                    <p>{"Has taken " + app.classes}</p>
                                    <p>Percent match: 90%</p>
                                    <Button onClick={() => {
                                        setAcceptStudent(true)

                                        let request = new BackendRequest("ChooseStudent", JSON.stringify({email: props.email, jobName: selectedJob.name}))

                                        let websocket = new WebSocket("ws://71.233.252.195:8129");
                                        websocket.onopen = () => {
                                            console.log(request);
                                            websocket.send(JSON.stringify(request));
                                        };
                                        websocket.onmessage = (event) => {
                                            console.log(event);
                                            websocket.close();
                                            loadStudents(selectedJob);
                                        };
                                    }} color={"success"} variant={"contained"} style={{
                                        borderRadius: 0,
                                        width: "50%",
                                        position: "absolute",
                                        bottom: 0,
                                        left: 0
                                    }}>Accept</Button>
                                    <Button onClick={() => {
                                        setRejectStudent(true)

                                        let request = new BackendRequest("ChooseStudent", JSON.stringify({email: props.email, jobName: selectedJob.name}))

                                        let websocket = new WebSocket("ws://71.233.252.195:8129");
                                        websocket.onopen = () => {
                                            console.log(request);
                                            websocket.send(JSON.stringify(request));
                                        };
                                        websocket.onmessage = (event) => {
                                            console.log(event);
                                            websocket.close();
                                            loadStudents(selectedJob);
                                        };
                                    }} color={"error"} variant={"contained"} style={{
                                        borderRadius: 0,
                                        width: "50%",
                                        position: "absolute",
                                        bottom: 0,
                                        right: 0
                                    }}>Reject</Button>
                                </div>)}
                            </div>
                            <IconButton onClick={() => slide("appContainer", 'right')}>
                                <ArrowCircleRight sx={{fontSize: 50}}/>
                            </IconButton>
                        </div>
                        <br/>
                        <span
                            style={{
                                marginLeft: "65px",
                                fontSize: "20px",
                                fontWeight: "bold"
                            }}>Recommended students:</span>
                        <div style={{marginTop: "10px", display: "flex", height: "35%", alignItems: "center"}}>
                            <IconButton onClick={() => slide("recContainer", 'left')}>
                                <ArrowCircleLeft sx={{fontSize: 50}}/>
                            </IconButton>
                            <div id="recContainer" style={{
                                padding: "5px",
                                position: "relative",
                                backgroundColor: "lightgray",
                                overflow: "auto",
                                whiteSpace: "nowrap",
                                overflowX: "scroll",
                                overflowY: "clip",
                                width: "85%",
                                height: "100%",
                                margin: "auto"
                            }}>
                                {appStudents.map((app: Student) => <div className={"studentList"} style={{
                                    position: "relative",
                                    overflowY: "auto",
                                    padding: "10px",
                                    fontSize: "12px",
                                    backgroundColor: "#FFF7D6",
                                    whiteSpace: "normal",
                                    display: "inline-block",
                                    margin: "5px",
                                    width: "170px",
                                    height: "90%"
                                }}>
                                    <p><b>{app.prefix + " " + app.fName + " " + app.lName + " " + app.suffix}</b></p>
                                    <p>{["Undergraduate", "Grad student", "Doctoral student", "Postdoc"][app.status] + ", grad year " + app.yearOfGraduation}</p>
                                    <p>{"Majoring in " + getStringWithConjunction(app.majors, "and")}</p>
                                    <p>{"Has taken " + getStringWithConjunction(app.classes, "and")}</p>
                                    <p>Percent match: 90%</p>
                                    <Button onClick={() => {
                                        setSuccessfullyConnected(true)
                                    }} color={"primary"} variant={"contained"} style={{
                                        borderRadius: 0,
                                        width: "100%",
                                        position: "absolute",
                                        bottom: 0,
                                        left: 0
                                    }}>Connect</Button>
                                </div>)}
                            </div>
                            <IconButton onClick={() => slide("recContainer", 'right')}>
                                <ArrowCircleRight sx={{fontSize: 50}}/>
                            </IconButton>
                        </div>
                        <Snackbar
                            open={rejectStudent}
                            autoHideDuration={3000}
                            onClose={() => {
                                setRejectStudent(false)
                            }}
                            message="The student's application has been removed"
                        />
                        <Snackbar
                            open={acceptStudent}
                            autoHideDuration={3000}
                            onClose={() => {
                                setAcceptStudent(false)
                            }}
                            message="The student has been accepted to the project and sent contact information"
                        />
                        <Snackbar
                            open={successfullyConnected}
                            autoHideDuration={3000}
                            onClose={() => {
                                setSuccessfullyConnected(false)
                            }}
                            message="The student has been sent a request to collaborate on this project"
                        />
                    </>}
                </div>
            </div>
        </div>
    );
}

export default FindStudentsPage;