package ru.itpark.services.programmatic;


import org.springframework.beans.factory.annotation.Value;
import ru.itpark.Cached;
import ru.itpark.models.PostModel;
import ru.itpark.utils.Parser;

import java.io.IOException;


public class ProgrammaticPostService {

    private final ProgrammaticRequestClient programmaticRequestClient;

    public ProgrammaticPostService() {
        programmaticRequestClient = new ProgrammaticRequestClient();
    }

    public ProgrammaticPostService(ProgrammaticRequestClient programmaticRequestClient) {
        this.programmaticRequestClient= programmaticRequestClient;
    }

    @Cached
    public PostModel getPost(@Value("${id}") Integer id) throws IOException {
            return Parser.getPostModel(programmaticRequestClient.getResponse(id));

    }
}
