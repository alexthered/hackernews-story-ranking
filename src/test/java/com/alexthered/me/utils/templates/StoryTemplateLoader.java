package com.alexthered.me.utils.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.alexthered.me.api.entity.Story;
import com.alexthered.me.utils.functions.LocalDateTimeFunction;
import com.alexthered.me.utils.functions.RandomString;

import java.time.LocalDateTime;

/**
 * Created by hd on 05.06.17.
 */
public class StoryTemplateLoader implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(Story.class).addTemplate("valid", new Rule(){{
            add("id", random(Integer.class, range(1L, 200L)));
            add("by", firstName());
            add("descendants", random(Integer.class, range(10, 100)));
            add("score", random(Integer.class, range(0, 1000)));
            add("time", new LocalDateTimeFunction(LocalDateTime.now(), LocalDateTime.now().minusHours(2)));
            add("title", new RandomString(10).nextString());
            add("url", new RandomString(10).nextString());
        }});
    }

}
