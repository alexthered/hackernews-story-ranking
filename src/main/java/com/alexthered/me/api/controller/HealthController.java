package com.alexthered.me.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hd on 05.06.17.
 */
@RestController
public class HealthController {

    @RequestMapping("/health")
    public String getHealth(){
        return "All is good";
    }
}
