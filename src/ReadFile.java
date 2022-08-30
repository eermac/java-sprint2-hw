import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class ReadFile {
    String path = "java-sprint2-hw" + File.separator + "resources" + File.separator ;
    public String readFileContentsOrNull(String path)
    {
        path = this.path + path;
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}
