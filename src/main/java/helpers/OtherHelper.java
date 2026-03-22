package helpers;

import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class OtherHelper {
    private List<Path> getJsonFiles() throws IOException {
        Path start = Path.of("src/test/resources");
        // JSON dosyalarını bul
        List<Path> pathList = Files.walk(start)
                .filter(Files::isRegularFile)
                .filter(p -> p.toString().endsWith(".json")).collect(Collectors.toList());
        return pathList;
    }

    public JsonNode getValue(String searchedKey) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Path> pathList = getJsonFiles();
        for (Path path : pathList) {
            JsonNode jsonArray = objectMapper.readTree(Files.newInputStream(path));
            if (jsonArray.isArray()) {
                for (JsonNode node : jsonArray) {
                    if(node.get("key").asString().equals(searchedKey)){
                        return node;
                    }
                }
            }
        }
        return null;
    }


}
