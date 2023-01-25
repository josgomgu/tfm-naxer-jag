package com.tfm.finalmaster.controllers;

import com.tfm.finalmaster.common.APIResponse;
import com.tfm.finalmaster.common.Util;
import com.tfm.finalmaster.dao.OptionMenuDao;
import com.tfm.finalmaster.models.OptionMenu;
import com.tfm.finalmaster.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
public class OptionMenuController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    OptionMenuDao optionMenuDao;

    @Autowired
    Util util;

    @RequestMapping(value = "api/optionsmenu")
    public ResponseEntity<APIResponse> getOptionsMenu(@RequestHeader(value = "Authorization") String token)
    {
        User u = util.checkToken(token);
        if(u != null) {
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setMessage("");
            apiResponse.setData(optionMenuDao.getOptionsMenu());
            apiResponse.setToken("");
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        }

        return util.getUnauthorizedResponse();

    }

    @RequestMapping(value = "api/optionsmenu/{optionmenuid}", method = RequestMethod.DELETE)
    public ResponseEntity<APIResponse> deleteOptionMenu(@PathVariable Long optionmenuid,@RequestHeader(value = "Authorization") String token)
    {
        User u = util.checkToken(token);
        if(u != null) {
            if(optionMenuDao.deleteOptionMenu(optionmenuid)) {
                apiResponse.setStatus(HttpStatus.OK.value());
                apiResponse.setMessage("Option Menu deleted!");
            }
            else {
                apiResponse.setStatus(HttpStatus.NOT_FOUND.value());
                apiResponse.setMessage("Option Menu not found!");
            }

            apiResponse.setData("");
            apiResponse.setToken("");
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        }

        return util.getUnauthorizedResponse();

    }

    @RequestMapping(value = "api/optionsmenu", method = RequestMethod.POST)
    public ResponseEntity<APIResponse> addOptionMenu(@RequestBody OptionMenu optionMenu, @RequestHeader(value = "Authorization") String token)
    {
        User u = util.checkToken(token);
        if(u != null) {

            optionMenu.setCreate_user(u.getUserid());
            optionMenu.setUpdate_user(u.getUserid());
            optionMenu.setCreation_date(new Date());
            optionMenu.setUpdate_date(new Date());
            optionMenu.setStatus('A');

            optionMenu = optionMenuDao.addOptionMenu(optionMenu);

            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setMessage("Option Menu Created!");
            apiResponse.setData("");
            apiResponse.setToken("");
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);

        }

        return util.getUnauthorizedResponse();

    }

    @RequestMapping(value = "api/optionsmenu", method = RequestMethod.PUT)
    public ResponseEntity<APIResponse> updateOptionMenu(@RequestBody OptionMenu optionMenu,@RequestHeader(value = "Authorization") String token)
    {

        User u = util.checkToken(token);
        if(u != null) {
            //Aud
            optionMenu.setUpdate_user(u.getUserid());
            optionMenu.setUpdate_date(new Date());
            optionMenuDao.updateOptionMenu(optionMenu);

            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setMessage("Option Menu Updated!");
            apiResponse.setData("");
            apiResponse.setToken("");
            return ResponseEntity.status(HttpStatus.OK.value()).body(apiResponse);
        }

        return util.getUnauthorizedResponse();

    }
}
