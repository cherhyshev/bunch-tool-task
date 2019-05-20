package pathProcessing;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathValidator {
    private static final PathValidator INSTANCE;

    static {
        try {
            INSTANCE = new PathValidator();
        } catch (Exception e) {
            throw new RuntimeException("Error while creating pathProcessing.PathValidator!", e);
        }
    }

    private PathValidator() {
    }

    public static PathValidator getInstance() {
        return INSTANCE;
    }


    public boolean isValid(String value) {
        Path path = Paths.get(value);
        return Files.exists(path);
    }
}
