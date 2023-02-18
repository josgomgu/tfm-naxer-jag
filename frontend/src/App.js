import React, { useState, useEffect } from "react";
import { Routes, Route, Link } from "react-router-dom";

import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import AuthService from "./services/auth.service";

import Login from "./components/Login";
import Home from "./components/Home";
import Profile from "./components/Profile";
import Users from "./components/user/ListUser";
import Roles from "./components/role/ListRole";
import UserPersist from "./components/user/PersistUser";
import RolePersist from "./components/role/PersistRole";
import Protected from "./common/Protected";

import EventBus from "./common/EventBus";


const App = () => {  
  const [currentUser, setCurrentUser] = useState(undefined);
  const [isLoggedIn, setisLoggedIn] = useState(null);

  useEffect(() => {
    const user = AuthService.getCurrentUser();

    //console.log("Este es el usuario conectado: "+JSON.stringify(user));
    if (user) {
      setCurrentUser(user);
      //setShowModeratorBoard(user.roles.includes("ROLE_MODERATOR"));
      //setShowAdminBoard(user.roles.includes("ROLE_ADMIN"));
     
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
          
          {currentUser && (
            <li className="nav-item">
              <Link to={"/users"} className="nav-link">
                Users
              </Link>
            </li>
          )}

          {currentUser && (
            <li className="nav-item">
              <Link to={"/roles"} className="nav-link">
                Roles
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
          <Route path="/"  element={<Protected isLoggedIn={isLoggedIn}><Home/></Protected>} />
          <Route path="/home"  element={<Protected isLoggedIn={isLoggedIn}><Home/></Protected>} />
          <Route path="/login" element={<Login/>} />         
          <Route path="/profile" element={<Protected isLoggedIn={isLoggedIn}><Profile/></Protected>} />          
          <Route path="/users" element={<Protected isLoggedIn={isLoggedIn}><Users/></Protected>} />
          <Route path="/users/:id" element={<Protected isLoggedIn={isLoggedIn}><UserPersist/></Protected>} />                           
          <Route path="/roles" element={<Protected isLoggedIn={isLoggedIn}><Roles/></Protected>} />
          <Route path="/roles/:id" element={<Protected isLoggedIn={isLoggedIn}><RolePersist/></Protected>} />                           
        </Routes>
      </div>    

    </div>
  );
};

export default App;
