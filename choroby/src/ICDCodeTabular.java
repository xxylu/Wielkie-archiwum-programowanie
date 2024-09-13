import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ICDCodeTabular {
    Map<String,String> ICDMap = new HashMap<>();

    public ICDCodeTabular(String path) {
        try (Stream<String> fileLines = Files.lines(Path.of(path))){
            ICDMap = fileLines.skip(87)
                    .map(String::trim)
                    .filter(line -> line.matches("[A-Z]\\d\\d.*"))
                    .map(line -> line.split(" ", 2))
                    .collect(Collectors.toMap(
                            part -> part[0],
                            part -> part[1].trim(),(oldV,neV) -> oldV));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  String getDescription(String code){
        return ICDMap.get(code);
    }
}
