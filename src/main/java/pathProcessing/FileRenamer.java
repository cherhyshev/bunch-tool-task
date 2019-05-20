package pathProcessing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class FileRenamer {
    private static final FileRenamer INSTANCE;

    static {
        try {
            INSTANCE = new FileRenamer();
        } catch (Exception e) {
            throw new RuntimeException("Error while creating pathProcessing.FileRenamer!", e);
        }
    }

    private FileRenamer() {
    }

    public static FileRenamer getInstance() {
        return INSTANCE;
    }

    public Set<File> rename(Set<File> filesToRename) {
        Set<File> newSet = new ConcurrentSkipListSet<>();
        filesToRename.parallelStream().forEach(it -> {
            try {
                Path newPath = Paths.get(it.getAbsolutePath() + ".2019");
                Files.move(it.toPath(), newPath, StandardCopyOption.REPLACE_EXISTING);
                newSet.add(newPath.toFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return newSet;
    }
}
