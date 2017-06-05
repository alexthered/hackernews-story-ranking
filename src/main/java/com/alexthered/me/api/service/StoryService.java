package com.alexthered.me.api.service;

import com.alexthered.me.api.entity.Story;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hd on 05.06.17.
 */
@Service
public class StoryService {

    public static final Integer TOP_STORIES_NUM = 10; //return 10 top stories at a time

    public List<Story> getTopStories() {
        return new ArrayList<>();
    }
}
