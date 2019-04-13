package ru.itpark.services.groovy;


import org.springframework.beans.factory.annotation.Value;
import ru.itpark.Cached;
import ru.itpark.models.PostModel;
import ru.itpark.utils.Parser;

import java.io.IOException;


public class GroovyPostService {

    private final GroovyRequestClient groovyRequestClient;

    public GroovyPostService() {
        groovyRequestClient = new GroovyRequestClient();
    }

    public GroovyPostService(GroovyRequestClient groovyRequestClient) {
        this.groovyRequestClient = groovyRequestClient;
    }

    @Cached
    public PostModel getPost(@Value("${id}") Integer id) throws IOException {
            return Parser.getPostModel(groovyRequestClient.getResponse(id));

    }
}
