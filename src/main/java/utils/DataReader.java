package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class DataReader {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static JsonNode getJsonNode(String path) {
        try (InputStream input = DataReader.class.getClassLoader().getResourceAsStream(path)) {
            return objectMapper.readTree(input);
        } catch (IOException ex) {
            LogUtils.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    public static String readEndpoint(String value) {
        return getJsonNode("endpoint.json").get(value).asText();
    }


    public static int readConfig(String value) {
        return getJsonNode("config.json").get(value).asInt();
    }

}

