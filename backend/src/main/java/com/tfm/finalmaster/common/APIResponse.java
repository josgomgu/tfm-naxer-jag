package com.tfm.finalmaster.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class APIResponse {

    @Getter @Setter
    private Integer status;
    @Getter @Setter
    private Object data;
    @Getter @Setter
    private Object message;
    @Getter @Setter
    private String token;


}