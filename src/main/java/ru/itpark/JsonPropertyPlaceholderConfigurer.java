package ru.itpark;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * Подставляет данные вместо плейсхолдеров из файла #properties.json
 */
public class JsonPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    //    public JsonPropertyPlaceholderConfigurer() throws FileNotFoundException {
//        setLocation(new ClassPathResource("properties.json"));
//
//        Properties properties = new Properties();
//        PostModel postModel = Parser.getPostModelByFileReader(new FileReader("./src/main/resources/properties.json"));
//        properties.setProperty("id", postModel.getId().toString());
//        setProperties(properties);
//    }
    public JsonPropertyPlaceholderConfigurer() {
        setLocation(new ClassPathResource("example.json"));
    }

/* TODO: В свое оправдание скажу, что пробовал по разному. Но это решение как и предыдущее не нравится!
    Скорее всего нужно копать в сторону #loadProperties */

    protected Properties mergeProperties() throws IOException {
        Properties result = new Properties();

        if (this.localOverride) {
            // Load properties from file upfront, to let local properties override.
            loadProperties(result);
        }

        if (this.localProperties != null) {
            for (Properties localProp : this.localProperties) {
                CollectionUtils.mergePropertiesIntoMap(localProp, result);
            }
        }

        if (!this.localOverride) {
            // Load properties from file afterwards, to let those properties override.
            loadProperties(result);
        }

        Map<String, String> map = new HashMap<>();
        for (final String name : result.stringPropertyNames())
            map.put(name.replaceAll("\"", ""), result.getProperty(name).replaceAll("\"", ""));

        Properties properties = new Properties();
        for (String value : map.keySet()) {
            properties.setProperty(value, map.get(value));
        }

        return properties;
    }

}
