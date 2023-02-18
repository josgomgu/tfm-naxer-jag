import { Navigate } from "react-router-dom";
import AuthService from "../services/auth.service";
import React  from 'react';

const Protected = ({ isLoggedIn, children }) => {
  const user = AuthService.getCurrentUser();
    
  if (!user) {
    return <Navigate to="/login" replace />;
  }
  return children;
};

export default Protected;