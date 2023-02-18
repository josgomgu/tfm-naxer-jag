import React from "react";
import AuthService from "../services/auth.service";
import { useNavigate } from 'react-router-dom';
import logo from '../assets/images/logo.png'; 

const user = AuthService.getCurrentUser();

const Home = () => {

  let navigate = useNavigate();
  if (!user) {    
     navigate("/login");
  }

    return (
      <div className="container">
        <header className="jumbotron">
          <b>Bienvenido a COMPRATHOR</b>
          <br></br>
          <br></br>
          Plataforma de Comparación de Productos 
          
        </header>
        <center><img src={logo} alt="TFM JAG"/></center>
        
      </div>
    );
  
};

export default Home;
