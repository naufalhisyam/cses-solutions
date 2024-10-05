import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;

public class InputUtils {

    public InputUtils() {}

    public static String readFileToString(String filePath) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            String input = br.readLine();
            while (input != null) {
                sb.append(input);
                sb.append("\n");
                input = br.readLine();
            }

            br.close();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return sb.toString();
    }

    public static void setInputFromString(String str) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        System.setIn(byteArrayInputStream);
    }
}
