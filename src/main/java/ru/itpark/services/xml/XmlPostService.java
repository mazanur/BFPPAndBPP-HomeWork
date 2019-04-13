package ru.itpark.services.xml;


import org.springframework.beans.factory.annotation.Value;
import ru.itpark.Cached;
import ru.itpark.models.PostModel;
import ru.itpark.utils.Parser;

import java.io.IOException;


public class XmlPostService {

    private final XmlRequestClient xmlRequestClient;

    public XmlPostService() {
        xmlRequestClient = new XmlRequestClient();
    }

    public XmlPostService(XmlRequestClient xmlRequestClient) {
        this.xmlRequestClient= xmlRequestClient;
    }

    @Cached
    public PostModel getPost(@Value("${id}") Integer id) throws IOException {
            return Parser.getPostModel(xmlRequestClient.getResponse(id));

    }
}
