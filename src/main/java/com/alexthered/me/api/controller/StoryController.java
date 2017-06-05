package com.alexthered.me.api.controller;

import com.alexthered.me.api.entity.Story;
import com.alexthered.me.api.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * Created by hd on 05.06.17.
 */
@RestController
public class StoryController {

    @Autowired
    StoryService storyService;


    @RequestMapping(path = "/stories/top", method = RequestMethod.GET)
    public List<Story> getTopStories(){
        return storyService.getTopStories();
    }
}
