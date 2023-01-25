package com.tfm.finalmaster.controllers;

import com.tfm.finalmaster.common.APIResponse;
import com.tfm.finalmaster.common.Util;
import com.tfm.finalmaster.dao.UserDao;
import com.tfm.finalmaster.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
public class UserController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    UserDao userDao;

    @Autowired
    Util util;

    @RequestMapping(value = "api/users")
    public ResponseEntity<APIResponse> getUsers(@RequestHeader(value = "Authorization") String token)
    {
        User u = util.checkToken(token);
        if(u != null) {
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setMessage("");
            apiResponse.setData(userDao.getUsers());
            apiResponse.setToken("");
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        }

        return util.getUnauthorizedResponse();

    }

    @RequestMapping(value = "api/users/{userid}", method = RequestMethod.DELETE)
    public ResponseEntity<APIResponse> deleteUser(@PathVariable Long userid,@RequestHeader(value = "Authorization") String token)
    {
        User u = util.checkToken(token);
        if(u != null) {
            if(userDao.deleteUser(userid)) {
                apiResponse.setStatus(HttpStatus.OK.value());
                apiResponse.setMessage("User deleted!");
            }
            else {
                apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                apiResponse.setMessage("User not found!");
            }

            apiResponse.setData("");
            apiResponse.setToken("");
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        }

        return util.getUnauthorizedResponse();

    }

    @RequestMapping(value = "api/users/{userid}", method = RequestMethod.GET)
    public ResponseEntity<APIResponse> getUser(@PathVariable Long userid,@RequestHeader(value = "Authorization") String token)
    {
        User u = util.checkToken(token);
        if(u != null) {
            User user = userDao.getUser(userid);
            if(user !=null) {
                apiResponse.setStatus(HttpStatus.OK.value());
                apiResponse.setMessage("");
                apiResponse.setData(user);
            }
            else {
                apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                apiResponse.setMessage("User not found!");
                apiResponse.setData("");
            }


            apiResponse.setToken("");
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        }

        return util.getUnauthorizedResponse();

    }

    @RequestMapping(value = "api/users", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> addUser(@RequestBody User user,@RequestHeader(value = "Authorization") String token)
    {
        User u = util.checkToken(token);
        if(u != null) {

            User chkUser = userDao.getUserByLogin(user.getLogin());

            if(chkUser == null) {
                Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
                String hash = argon2.hash(1, 1024, 1, user.getPassword());
                user.setPassword(hash);
                //Aud
                user.setCreate_user(u.getUserid());
                user.setUpdate_user(u.getUserid());
                user.setCreation_date(new Date());
                user.setUpdate_date(new Date());
                user.setStatus('A');

                user = userDao.addUser(user);

                apiResponse.setStatus(HttpStatus.OK.value());
                apiResponse.setMessage("User Created!");
                apiResponse.setData("");
                apiResponse.setToken("");
                return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
            }else{
                apiResponse.setStatus(HttpStatus.IM_USED.value());
                apiResponse.setMessage("User login exists. User Not Created!");
                apiResponse.setData("");
                apiResponse.setToken("");
                return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
            }
        }

        return util.getUnauthorizedResponse();

    }

    @RequestMapping(value = "api/users", method = RequestMethod.PUT)
    public ResponseEntity<APIResponse> updateUser(@RequestBody User user,@RequestHeader(value = "Authorization") String token)
    {

        User u = util.checkToken(token);
        if(u != null) {
            //Aud
            user.setUpdate_user(u.getUserid());
            user.setUpdate_date(new Date());
            userDao.updateUser(user);

            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setMessage("User Updated!");
            apiResponse.setData("");
            apiResponse.setToken("");
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        }

        return util.getUnauthorizedResponse();

    }
}
