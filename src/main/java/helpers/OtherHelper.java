package helpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class OtherHelper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private List<Path> getJsonFiles() throws IOException {
        Path start = Path.of("src/test/resources");

        return Files.walk(start)
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith(".json"))
                .collect(Collectors.toList());
    }

    public JsonNode getValue(String searchedKey) throws IOException {
        List<Path> pathList = getJsonFiles();

        for (Path path : pathList) {
            JsonNode jsonArray = objectMapper.readTree(Files.newInputStream(path));

            if (jsonArray.isArray()) {
                for (JsonNode node : jsonArray) {

                    JsonNode keyNode = node.get("key");

                    if (keyNode != null && searchedKey.equals(keyNode.asText())) {
                        return node;
                    }
                }
            }
        }
        return null;
    }
}