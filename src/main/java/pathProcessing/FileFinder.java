package pathProcessing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class FileFinder {
    private static final FileFinder INSTANCE;
    private final Set<File> foundedFiles;

    static {
        try {
            INSTANCE = new FileFinder();
        } catch (Exception e) {
            throw new RuntimeException("Error while creating pathProcessing.FileFinder!", e);
        }
    }

    private FileFinder() {
        foundedFiles = new ConcurrentSkipListSet<>();
    }

    public static FileFinder getInstance() {
        return INSTANCE;
    }

    public Set<File> findFiles(String pathStr) throws IOException {
        foundedFiles.clear();
        Files.find(Paths.get(pathStr),
                Integer.MAX_VALUE,
                (filePath, fileAttr) -> fileAttr.isRegularFile())
                .filter(it -> it.getFileName().toString().endsWith(".java")
                        || it.getFileName().toString().endsWith(".kt"))
                .forEach(it -> foundedFiles.add(it.toFile()));
        return foundedFiles;
    }


}
