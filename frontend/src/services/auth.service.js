
import axios from "axios";


const API_URL = "http://localhost:9090/api/";



const register = (username, email, password) => {
  return axios.post(API_URL + "signup", {
    username,
    email,
    password,
  });
};

const login =  (username, password) => {
 
  let data = JSON.stringify({
    "login": username,
    "password": password
  });

  var config = {
    method: 'POST',
    headers: {      
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Access-Control-Allow-Methods': 'DELETE, POST, GET, OPTIONS'
    },    
  };
  
  return axios
    .post(API_URL + "login", data,config)
    .then((response) => {
      if (response.data) {
        console.log("Usuario OK");
        localStorage.setItem("user", JSON.stringify(response.data.data));
        localStorage.setItem("token", JSON.stringify(response.data.token));
      }

      return response.data;
    });
    
};

const logout = () => {
  localStorage.removeItem("user");
  localStorage.removeItem("token");
};

const getCurrentUser = () => {
  return JSON.parse(localStorage.getItem("user"));
};

const getCurrentToken = () => {
  return localStorage.getItem("token");
};

const AuthService = {
  register,
  login,
  logout,
  getCurrentUser,
  getCurrentToken,
};

export default AuthService;
