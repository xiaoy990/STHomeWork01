import java.text.ParseException;

public class SMTest {
    public static void main(String[] args) {
        StudentManager instance = StudentManager.getInstance();
        try {
            instance.APP();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
