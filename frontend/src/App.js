import React, { useState, useEffect } from "react";
import { Routes, Route, Link } from "react-router-dom";

import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AuthService from "./services/auth.service";

import Login from "./components/Login";
import Home from "./components/Home";
import Profile from "./components/Profile";
import BoardUser from "./components/BoardUser";
import BoardModerator from "./components/BoardModerator";
import BoardAdmin from "./components/BoardAdmin";
import Users from "./components/user/ListUser";
import UserPersist from "./components/user/PersistUser";
import Protected from "./common/Protected";


import EventBus from "./common/EventBus";


const App = () => {
  const [showModeratorBoard, setShowModeratorBoard] = useState(false);
  const [showAdminBoard, setShowAdminBoard] = useState(false);
  const [currentUser, setCurrentUser] = useState(undefined);
  const [isLoggedIn, setisLoggedIn] = useState(null);

  useEffect(() => {
    const user = AuthService.getCurrentUser();

    console.log("Este es el usuario conectado: "+JSON.stringify(user));
    if (user) {
      setCurrentUser(user);
      //setShowModeratorBoard(user.roles.includes("ROLE_MODERATOR"));
      //setShowAdminBoard(user.roles.includes("ROLE_ADMIN"));
      setShowModeratorBoard(true);
      setShowAdminBoard(true);
      setisLoggedIn(true);
    }
    else
    {
      setisLoggedIn(false);
    }

    EventBus.on("logout", () => {
      logOut();
    });

    return () => {
      EventBus.remove("logout");
    };
    
  }, []);

  const logOut = () => {
    AuthService.logout();
    setShowModeratorBoard(false);
    setShowAdminBoard(false);
    setCurrentUser(undefined);
  };

  return (
    <div>
      <nav className="navbar navbar-expand navbar-dark bg-dark">
        <Link to={"/"} className="navbar-brand">
          Comprathor
        </Link>
        <div className="navbar-nav mr-auto">
        {currentUser && (
          <li className="nav-item">
            <Link to={"/home"} className="nav-link">
              Home
            </Link>
          </li>
        )}

          {showModeratorBoard && (
            <li className="nav-item">
              <Link to={"/mod"} className="nav-link">
                User Board
              </Link>
            </li>
          )}

          {showAdminBoard && (
            <li className="nav-item">
              <Link to={"/admin"} className="nav-link">
                Admin Board
              </Link>
            </li>
          )}

          {currentUser && (
            <li className="nav-item">
              <Link to={"/users"} className="nav-link">
                Users
              </Link>
            </li>
          )}
        </div>

        {currentUser ? (
          <div className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link to={"/profile"} className="nav-link">
                {currentUser.name} {currentUser.last_name}
              </Link>
            </li>
            <li className="nav-item">
              <a href="/login" className="nav-link" onClick={logOut}>
                LogOut
              </a>
            </li>
          </div>
        ) : (
          <div className="navbar-nav ml-auto">
            <li className="nav-item">
              <Link to={"/login"} className="nav-link">
                Login
              </Link>
            </li>
            
          </div>
        )}
      </nav>

      <div className="container mt-3">
        <Routes>
          <Route path="/" element={<Protected isLoggedIn={isLoggedIn}><Home/></Protected>} />
          <Route path="/home" element={<Protected isLoggedIn={isLoggedIn}><Home/></Protected>} />
          <Route path="/login" element={<Login/>} />         
          <Route path="/profile" element={<Protected isLoggedIn={isLoggedIn}><Profile/></Protected>} />
          <Route path="/user" element={<BoardUser/>} />
          <Route path="/mod" element={<BoardModerator/>} />
          <Route path="/admin" element={<BoardAdmin/>} />
          <Route path="/users" element={<Protected isLoggedIn={isLoggedIn}><Users/></Protected>} />
          <Route path="/users/:id" element={<Protected isLoggedIn={isLoggedIn}><UserPersist/></Protected>} />                  
        </Routes>
      </div>    

    </div>
  );
};

export default App;
