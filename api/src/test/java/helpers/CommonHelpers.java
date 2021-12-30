package helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class CommonHelpers {

    public static String getDataFromJson(String jsonName) {
        String data = "";
        String pathToFile = String.format("/src/test/resources/jsons/%s.json", jsonName);
        Path fullPath = Paths.get(Paths.get("").toAbsolutePath() + pathToFile);
        try {
            data = Files.lines(fullPath)
                    .collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String prepareBodyFromJson(String json, String... variables) {
        return String.format(json, variables);
    }
}