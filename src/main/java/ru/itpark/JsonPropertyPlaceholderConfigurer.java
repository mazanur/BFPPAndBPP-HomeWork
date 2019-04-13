package ru.itpark;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import ru.itpark.models.PostModel;
import ru.itpark.utils.Parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;


/**
 * Подставляет данные вместо плейсхолдеров из файла #properties.json
 */
public class JsonPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    public JsonPropertyPlaceholderConfigurer() throws FileNotFoundException {
        Properties properties = new Properties();
        PostModel postModel = Parser.getPostModelByFileReader(new FileReader("./src/main/resources/properties.json"));
        properties.setProperty("id", postModel.getId().toString());
        setProperties(properties);
    }
}
