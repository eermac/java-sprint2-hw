import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadFile {
    //Использовал полный путь, т.к. в моей системе не отрабатывает более короткий
    String path = "C:\\Users\\eermachenkov\\dev\\second-project\\java-sprint2-hw\\resources\\";
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
