import React from "react";
import AuthService from "../services/auth.service";

const Profile = () => {
  const currentUser = AuthService.getCurrentUser();
  const currentToken = AuthService.getCurrentToken();

  return (
    <div className="container">
      <header className="jumbotron">
        <h3>
          <strong>{currentUser.login}</strong> Profile
        </h3>
      </header>
      <p>
        <strong>Token:</strong> {currentToken.substring(0, 20)} ...{" "}
        {currentToken.substr(currentToken.length - 20)}
      </p>
      <p>
        <strong>Id:</strong> {currentUser.userid}
      </p>
      <p>
        <strong>Name:</strong> {currentUser.name}
      </p>
      <p>
        <strong>Last Name:</strong> {currentUser.last_name}
      </p>
      
      
      
    </div>
  );
};

export default Profile;
