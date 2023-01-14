import React, {useState} from 'react';
import logo from './logo.svg';
import './App.css';

function App() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = (e: { preventDefault: () => void; }) => {
    e.preventDefault();
    // Send a request to the server with the entered username and password
    // and handle the response here
      const websocket = new WebSocket("ws://localhost:8000");
      console.log(websocket.readyState == WebSocket.OPEN);
      if (websocket.readyState === WebSocket.OPEN){
          console.log("THE SOCKET IS OPEN");
      }
      websocket.onopen = () => {
          console.log("MESSAGE SENT");
          websocket.send("HELLO I AM A WEBSOCKET");
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
