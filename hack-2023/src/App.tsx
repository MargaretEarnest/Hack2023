import React from 'react';
import './App.css';
import MenuBar from "./Components/MenuBar";
import HomePage from "./Pages/HomePage";
import CreateAccount from "./Pages/SignupPage";
import {HashRouter as Router, Route, Routes} from "react-router-dom";
import FindJobPage from "./Pages/FindJobPage";
import FindStudentsPage from "./Pages/FindStudentsPage";

function App() {
    const [username, setUsername] = React.useState<string>("");
    const [email, setEmail] = React.useState<string>("");
    const [accountType, setAccountType] = React.useState("");

    return (
        <Router>
            <div className={"pageLayout"}>
                <MenuBar username={username} setEmail={setEmail} setUsername={setUsername} accountType={accountType}
                         setAccountType={setAccountType}/>
                <Routes>
                    <Route path="/" element={username == "" ? <HomePage/> : (accountType == "student") ? <FindJobPage email={email}/>
                        : <FindStudentsPage currentUser={username} email={email}/>}/>
                    <Route path="/createAccount" element={<CreateAccount/>}/>
                </Routes>
            </div>
        </Router>
    );
}

export default App;