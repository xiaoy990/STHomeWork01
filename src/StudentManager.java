import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StudentManager {
    List<Student> stuList;
    private static volatile StudentManager INSTANCE;
    private Scanner scanner;

    private StudentManager(){this.stuList = new ArrayList<>(); this.scanner = new Scanner(System.in);}

    public static StudentManager getInstance(){
        if (INSTANCE==null){
            synchronized (StudentManager.class){
                if (INSTANCE==null){
                    INSTANCE = new StudentManager();
                }
            }
        }
        return INSTANCE;
    }


}
