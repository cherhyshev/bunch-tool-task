import io.TipsHelper;
import pathProcessing.FileFinder;
import pathProcessing.FileRenamer;
import pathProcessing.PathValidator;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class RenamerApp {
    private PathValidator validator = PathValidator.getInstance();
    private FileFinder finder = FileFinder.getInstance();
    private FileRenamer renamer = FileRenamer.getInstance();

    public void run() throws IOException {
        TipsHelper.printGreeting();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String inputPath = scanner.next();
            if (!validator.isValid(inputPath)) {
                System.err.println("Input path " + inputPath + " is not valid!");
                System.out.println("Try again");
            } else {
                System.out.println("Founded files for renaming:");
                Set<File> foundedFiles = finder.findFiles(inputPath);
                foundedFiles.forEach(it -> System.out.println(it.getAbsolutePath()));
                Set<File> renamedFiles = renamer.rename(foundedFiles);
                System.out.println("Files after renaming:");
                renamedFiles.forEach(it -> System.out.println(it.getAbsolutePath()));
            }

        }

    }
}
