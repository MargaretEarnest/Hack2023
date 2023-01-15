import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import {createTheme, ThemeProvider} from "@mui/material";
import App from "./App";

const root = ReactDOM.createRoot(
    document.getElementById('root') as HTMLElement
);

const theme = createTheme({
    palette: {
        primary: {
            main: "#0B3D91"
        },
        secondary: {
            main: "#B8D5ED"
        },
        info: {
            main: "#F1F1E6"
        },
        warning: {
            main: "#FFF7D6",
        },
        error: {
            main: "#BF0000"
        },
        success: {
            main: "#70A15F"
        }
    },
});

root.render(
    <ThemeProvider theme={theme}>
        <App/>
    </ThemeProvider>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
