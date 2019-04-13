package ru.itpark.utils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import ru.itpark.models.PostModel;

import java.io.FileReader;

public class Parser {

    public static PostModel getPostModel(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, PostModel.class);
    }
    public static String convertJsonFileToString(FileReader fileReader){
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = (JsonArray) parser.parse(fileReader);
        return jsonArray.get(0).toString();
    }
    public static PostModel getPostModelByFileReader(FileReader fileReader) {
        return getPostModel(convertJsonFileToString(fileReader));

    }

}
