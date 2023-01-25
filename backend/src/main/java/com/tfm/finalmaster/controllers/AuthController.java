package com.tfm.finalmaster.controllers;

import com.tfm.finalmaster.common.APIResponse;
import com.tfm.finalmaster.dao.UserDao;
import com.tfm.finalmaster.models.User;
import com.tfm.finalmaster.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class AuthController {

    @Autowired
    UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil ;

    @Autowired
    private APIResponse apiResponse;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> login(@RequestBody User user)
    {
        User userAuth = userDao.getUserCredentials(user);

        if(userAuth != null) {

            String tokenJWT = jwtUtil.create(String.valueOf(userAuth.getUserid()),userAuth.getName());
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setMessage("User logged in!");
            apiResponse.setData(userAuth);
            apiResponse.setToken(tokenJWT);

            userAuth.setLast_login(new Date());
            userDao.updateUser(userAuth);

            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        }
        else {
            apiResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            apiResponse.setMessage("User login failed");
            apiResponse.setData("");
            apiResponse.setToken("");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(apiResponse);
        }
    }
    
}
