import * as React from 'react';
import SchoolTwoToneIcon from '@mui/icons-material/SchoolTwoTone';
import BackpackTwoToneIcon from '@mui/icons-material/BackpackTwoTone';
import CircleTwoToneIcon from '@mui/icons-material/CircleTwoTone';
import {IconButton, Typography} from "@mui/material";

function SignupPage() {

    return (
        <div className="backgroundBox">
            <h1 id="signUpAs">Sign Up as a...</h1>
            <div style={{display: "flex", justifyContent: "space-evenly", height: "60%"}}>
                <div className={"profileTypeHolder"}>
                    <IconButton style={{width: "400px", height: "400px", position: "absolute"}}>
                        <CircleTwoToneIcon color="secondary" style={{fontSize: "400px", position: "absolute"}}/>
                        <BackpackTwoToneIcon color="primary" style={{fontSize: "200px", position: "absolute"}}/>
                        <Typography color={"primary"} fontWeight={"900"} fontSize={"70px"}
                                    style={{position: "absolute", bottom: "-80px"}}>Student</Typography>
                    </IconButton>
                </div>
                <div className={"profileTypeHolder"}>
                    <IconButton style={{width: "400px", height: "400px", position: "absolute"}}>
                        <CircleTwoToneIcon color="secondary" style={{fontSize: "400px", position: "absolute"}}/>
                        <SchoolTwoToneIcon color="primary" style={{fontSize: "200px", position: "absolute"}}/>
                        <Typography color={"primary"} fontWeight={"900"} fontSize={"70px"}
                                    style={{position: "absolute", bottom: "-80px"}}>Professor</Typography>
                    </IconButton>
                </div>
            </div>
        </div>
    );
}

export default SignupPage;