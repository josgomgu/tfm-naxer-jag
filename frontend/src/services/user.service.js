import axios from "axios";
import authHeader from "./auth-header";


const API_URL = "http://localhost:9090/api/users";


const getPublicContent = () => {
  return axios.get(API_URL);
};

const getUserBoard = () => {
  return axios.get(API_URL + "user", { headers: authHeader() });
};

const getModeratorBoard = () => {
  return axios.get(API_URL + "mod", { headers: authHeader() });
};

const getAdminBoard = () => {
  return axios.get(API_URL + "admin", { headers: authHeader() });
};

const addUser = (user) => {

  var data = JSON.stringify(user);
  
  var config = {
    method: 'post',
    url: API_URL,
    headers: authHeader(),
    data : data
  };


  return axios(config).catch(function (error) {
    //console.log("JAG: ");
  });
  

};

const updateUser = (user) => {
  return axios.put(API_URL, { headers: authHeader(),data : user });
};

const getUsers = () =>{
  return axios.get(API_URL, { headers: authHeader() });
}

const getUserById = (id) =>{
  
  return axios.get(API_URL + '/' + id , { headers: authHeader() }).catch(function (error) {
    return null;
  });
}

const UserService = {
  getPublicContent,
  getUserBoard,
  getModeratorBoard,
  getAdminBoard,
  getUsers,
  addUser,
  updateUser,
  getUserById
};

export default UserService;
