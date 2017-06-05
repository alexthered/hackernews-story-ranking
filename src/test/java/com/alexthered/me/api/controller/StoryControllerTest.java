package com.alexthered.me.api.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.alexthered.me.api.entity.Story;
import com.alexthered.me.api.service.StoryService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by hd on 05.06.17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = StoryController.class, secure = false)
public class StoryControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StoryService mockedStoryService;

    @BeforeClass
    public static void setUp() {
        FixtureFactoryLoader.loadTemplates("com.alexthered.me.utils.templates");
    }

    @Test
    public void getTopStories() throws Exception {
        List<Story> clients = Fixture.from(Story.class).gimme(10, "valid");

        given(mockedStoryService.getTopStories()).willReturn(clients);

        mockMvc.perform(get("/stories/top")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

}