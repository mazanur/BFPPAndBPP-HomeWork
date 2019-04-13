package ru.itpark.services.java;


import org.springframework.beans.factory.annotation.Value;
import ru.itpark.Cached;
import ru.itpark.models.PostModel;
import ru.itpark.utils.Parser;

import java.io.IOException;


public class JavaPostService {

    private final JavaRequestClient javaRequestClient;

    public JavaPostService() {
        javaRequestClient = new JavaRequestClient();
    }

    public JavaPostService(JavaRequestClient javaRequestClient) {
        this.javaRequestClient = javaRequestClient;
    }

    @Cached
    public PostModel getPost(@Value("${id}") Integer id) throws IOException {
            return Parser.getPostModel(javaRequestClient.getResponse(id));

    }
}
