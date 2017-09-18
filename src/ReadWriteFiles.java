import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadWriteFiles {
    public String readFile(String path) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, Charset.defaultCharset()).toUpperCase();
        } catch (IOException e) {
            System.err.println("File is not exist!");
        }
        return null;
    }

    public void writeFile(String path, String s) {
        try {
            Files.write(Paths.get(path), s.getBytes(Charset.defaultCharset()));
        } catch (IOException e) {
            System.err.println("File is not exist!");
        }
    }
}
