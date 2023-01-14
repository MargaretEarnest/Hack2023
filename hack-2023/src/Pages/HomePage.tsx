import * as React from 'react';
import researchers from '../images/researchers.jpg';

function HomePage() {
    window.setInterval(
        function () {
            let cursor = document.getElementById("cursor");
            if (cursor) {
                if (cursor.style.visibility == "visible") {
                    cursor.style.visibility = "hidden";
                } else {
                    cursor.style.visibility = "visible";
                }
            }
        }, 1000
    );

    return (
        <div>
            <div style={{display: "flex", flexDirection: "column", alignItems: "center", justifyContent: "center"}}>
                <img width={"100%"} height={"auto"} style={{opacity: "80%"}} src={researchers}/>
                <h1 className="noselect" style={{position: "absolute", fontSize: "1000%"}}>
                    reSearch<span id="cursor" style={{visibility: "visible"}}>_</span>
                </h1>
            </div>
            <div style={{display: "flex", width: "100%", justifyContent: "center", flexDirection: "column", alignItems: "center"}}>
                <h2>Simplifying your Search</h2>
                <p style={{width: "50%"}}>Finding the right people to change the world with takes a lot of time. At reSearch_,
                    we strive to take the stress out of
                    applying for and advertising research positions. Professors and employers looking to hire researchers
                    can post job descriptions on the website and set requirements for candidates to easily find the best
                    qualified candidates available. Students can browse posted job descriptions and apply to positions
                    that match their interest.</p>
            </div><br/><br/>
        </div>
    );
}

export default HomePage;