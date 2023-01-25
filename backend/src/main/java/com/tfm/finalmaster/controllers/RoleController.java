package com.tfm.finalmaster.controllers;

import com.tfm.finalmaster.common.APIResponse;
import com.tfm.finalmaster.common.Util;
import com.tfm.finalmaster.dao.RoleDao;
import com.tfm.finalmaster.models.Role;
import com.tfm.finalmaster.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
public class RoleController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    RoleDao roleDao;

    @Autowired
    Util util;

    @RequestMapping(value = "api/roles")
    public ResponseEntity<APIResponse> getRoles(@RequestHeader(value = "Authorization") String token)
    {
        User u = util.checkToken(token);
        if(u != null) {
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setMessage("");
            apiResponse.setData(roleDao.getRoles());
            apiResponse.setToken("");
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        }

        return util.getUnauthorizedResponse();

    }

    @RequestMapping(value = "api/roles/{roleid}", method = RequestMethod.DELETE)
    public ResponseEntity<APIResponse> deleteRole(@PathVariable Long roleid,@RequestHeader(value = "Authorization") String token)
    {
        User u = util.checkToken(token);
        if(u != null) {
            if(roleDao.deleteRole(roleid)) {
                apiResponse.setStatus(HttpStatus.OK.value());
                apiResponse.setMessage("Role deleted!");
            }
            else {
                apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                apiResponse.setMessage("Role not found!");
            }

            apiResponse.setData("");
            apiResponse.setToken("");
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        }

        return util.getUnauthorizedResponse();

    }

    @RequestMapping(value = "api/roles", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> addRole(@RequestBody Role role, @RequestHeader(value = "Authorization") String token)
    {
        User u = util.checkToken(token);
        if(u != null) {

            role.setCreate_user(u.getUserid());
            role.setUpdate_user(u.getUserid());
            role.setCreation_date(new Date());
            role.setUpdate_date(new Date());
            role.setStatus('A');

            role = roleDao.addRole(role);

            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setMessage("Role Created!");
            apiResponse.setData("");
            apiResponse.setToken("");
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);

        }

        return util.getUnauthorizedResponse();

    }

    @RequestMapping(value = "api/roles", method = RequestMethod.PUT)
    public ResponseEntity<APIResponse> updateUser(@RequestBody Role role,@RequestHeader(value = "Authorization") String token)
    {

        User u = util.checkToken(token);
        if(u != null) {
            //Aud
            role.setUpdate_user(u.getUserid());
            role.setUpdate_date(new Date());
            roleDao.updateRole(role);

            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setMessage("Role Updated!");
            apiResponse.setData("");
            apiResponse.setToken("");
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        }

        return util.getUnauthorizedResponse();

    }
}
