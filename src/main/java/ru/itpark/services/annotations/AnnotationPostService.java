package ru.itpark.services.annotations;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itpark.Cached;
import ru.itpark.models.PostModel;
import ru.itpark.utils.Parser;

import java.io.IOException;


@Component("service")
public class AnnotationPostService {

    private final AnnotationRequestClient annotationRequestClient;

    public AnnotationPostService() {
        annotationRequestClient = new AnnotationRequestClient();
    }

    public AnnotationPostService(AnnotationRequestClient annotationRequestClient) {
        this.annotationRequestClient= annotationRequestClient;
    }

    @Cached
    public PostModel getPost(@Value("${id}") Integer id) throws IOException {
            return Parser.getPostModel(annotationRequestClient.getResponse(id));

    }
}
