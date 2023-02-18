import axios from "axios";
import authHeader from "./auth-header";


const API_URL = process.env.REACT_APP_API_URL+"/roles";


const addRecord = (record) => {

  var data = JSON.stringify(record);
  
  var config = {
    method: 'post',
    url: API_URL,
    headers: authHeader(),
    data : data
  };


  return axios(config).catch(function (error) {
    
  });
  

};

const updateRecord = (record) => {
  var data = JSON.stringify(record);
  
  var config = {
    method: 'put',
    url: API_URL,
    headers: authHeader(),
    data : data
  };


  return axios(config).catch(function (error) {    
  });
};

const getRecords = () =>{
  return axios.get(API_URL, { headers: authHeader() });
}

const getRecordById = (id) =>{
  
  return axios.get(API_URL + '/' + id , { headers: authHeader() }).catch(function (error) {
    return null;
  });
}

const deleteRecord = (id) =>{
  
  return axios.delete(API_URL + '/' + id , { headers: authHeader() }).catch(function (error) {
    return null;
  });
}

const RoleService = {
  getRecords,
  addRecord,
  updateRecord,
  getRecordById,
  deleteRecord
};

export default RoleService;
