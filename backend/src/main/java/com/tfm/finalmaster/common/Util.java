package com.tfm.finalmaster.common;

import com.tfm.finalmaster.dao.UserDao;
import com.tfm.finalmaster.models.User;
import com.tfm.finalmaster.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Util {

    @Autowired
    private JWTUtil jwtUtil ;

    @Autowired
    UserDao userDao;

    @Autowired
    APIResponse apiResponse;

    public User checkToken(String token)
    {
        try {
            String userid = jwtUtil.getKey(token);
            //System.out.println("Userid: "+userid);
            if (userid != null) {
                User u = userDao.getUser(Long.parseLong(userid));
                return u;
            }

            return null;
        }catch (Exception e) {

            return null;
        }
    }

    public ResponseEntity getUnauthorizedResponse()
    {
        apiResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        apiResponse.setMessage("Unauthorized");
        apiResponse.setData("");
        apiResponse.setToken("");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(apiResponse);
    }
}
