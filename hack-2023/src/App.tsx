import React from 'react';
import './App.css';
import MenuBar from "./Components/MenuBar";
import HomePage from "./Pages/HomePage";
import CreateAccount from "./Pages/SignupPage";
import {HashRouter as Router, Route, Routes} from "react-router-dom";
import FindJobPage from "./Pages/FindJobPage";
import FindStudentsPage from "./Pages/FindStudentsPage";
import EditProfilePage from "./Pages/EditProfilePage";

function App() {
    const [username, setUsername] = React.useState("");

    return (
        <Router>
            <div className={"pageLayout"}>
                <MenuBar username={username} setUsername={setUsername}/>
                <Routes>
                    <Route path="/" element={<HomePage/>}/>
                    <Route path="/createAccount" element={<CreateAccount/>}/>
                    <Route path="/findJob" element={<FindJobPage/>}/>
                    <Route path="/findStudents" element={<FindStudentsPage/>}/>
                    <Route path="/editProfile" element={<EditProfilePage/>}/>
                </Routes>
            </div>
        </Router>
    );

export default App;