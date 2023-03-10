import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogContent from '@mui/material/DialogContent';
import DialogTitle from '@mui/material/DialogTitle';
import CloseIcon from "@mui/icons-material/Close";
import DialogActions from "@mui/material/DialogActions";
import {Link} from "@mui/material";
import {Login} from "../jsonObjects/Login";
import {BackendRequest} from "../jsonObjects/BackendRequest";

export default function LoginDialog(props: { open: boolean, handleClose: () => void, setEmail: any, setUsername: any, setAccountType: any }) {
    return (
        <Dialog open={props.open} fullWidth maxWidth="xs" onClose={props.handleClose}>
            <DialogActions>
                <Button onClick={props.handleClose} style={{minWidth: 0}}><CloseIcon/></Button>
            </DialogActions>
            <DialogTitle paddingBottom={"0px !important"} fontSize={"30px !important"}
                         align={"center"} marginTop={"-30px"}>Login</DialogTitle>
            <br/>
            <DialogContent style={{textAlign: "center"}}>
                <TextField
                    autoFocus
                    id="email"
                    label="Email"
                    type="text"
                    variant="standard"
                /><br/>
                <TextField
                    id="password"
                    label="Password"
                    type="password"
                    variant="standard"
                /><br/><br/><br/>
                <Button variant="contained" style={{width: "150px", marginBottom: "10px"}} onClick={() => {
                    console.log("Sending Message");
                    let websocket = new WebSocket("ws://71.233.252.195:8129");
                    websocket.onopen = () => {
                        websocket.send(JSON.stringify(new BackendRequest("ValidateUser", JSON.stringify(new Login(getById("email"), getById("password"))))));
                    };
                    websocket.onmessage = (event) => {
                        console.log("Got Message");
                        console.log(event);
                        let data = JSON.parse(event.data);
                        if (data.isValid) {
                            props.setUsername(data.name);
                            props.setAccountType(data.isStudent ? "student" : "professor");
                            props.setEmail(getById("email"));
                            props.handleClose();
                        } else {
                            alert("Login incorrect");
                        }
                        websocket.close();
                    };
                }}>Login</Button><br/>
                <Link className="noSelect" style={{cursor: "pointer"}} href="#/createAccount" onClick={props.handleClose}>Create an Account</Link>
                <br/><br/>
            </DialogContent>
        </Dialog>
    );
}

export function getById(id: string) {
    return (document.getElementById(id) as HTMLInputElement)?.value;
}