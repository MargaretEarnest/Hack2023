import React, {useState} from 'react';
import logo from './logo.svg';
import './App.css';
import {Login} from "./jsonObjects/Login";
import {Request} from "./jsonObjects/Request";

function App() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (e: { preventDefault: () => void; }) => {
    e.preventDefault();
    // Send a request to the server with the entered username and password
    // and handle the response here
      const websocket = new WebSocket("ws://localhost:8000");
      websocket.onopen = () => {
          let login = new Login(username, password);
          websocket.send(JSON.stringify(new Request("ValidateUser", JSON.stringify(login))));
      };
      websocket.onmessage = (event) => {
          console.log(event);
      };
  };

  return (
      <form onSubmit={handleSubmit}>
        <label>
          Username:
          <input
              type="text"
              value={username}
              onChange={e => setUsername(e.target.value)}
          />
        </label>
        <br />
        <label>
          Password:
          <input
              type="password"
              value={password}
              onChange={e => setPassword(e.target.value)}
          />
        </label>
        <br />
        <button type="submit">Login</button>
      </form>
  );
}

export default App;
