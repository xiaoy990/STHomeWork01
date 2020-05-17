import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StudentManager {
    List<Student> stuList;
    private static volatile StudentManager INSTANCE;
    private Scanner scanner;

    private StudentManager(){this.stuList = new ArrayList<>(); this.scanner = new Scanner(System.in);}

    /**
     * 获取manager的单例对象
     * @return 学生管理器实例
     */
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

    /**
     * APP的启动方法
     * @throws ParseException 解析异常
     * @throws IOException IO异常
     */
    public void APP() throws ParseException, IOException {
        printBanner();
        while (scanner.hasNextInt()){
            int item = scanner.nextInt();
            switch (item){
                case 1:{
                    System.out.println("请依次输入 学号、姓名、性别(true为女,false为男)、生日(2020-04-28)");
                    Student student = new Student();
                    student.setId(scanner.nextInt());
                    student.setName(scanner.next());
                    student.setGender(scanner.nextBoolean());
                    student.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse(scanner.next()));
                    insertStudent(student);
                    break;
                }
                case 2:{
                    System.out.println("输入要查找的学生姓名:");
                    queryStudentByName(scanner.next()).forEach(System.out::println);
                    break;
                }
                case 3:{
                    System.out.println("请输入要删除的学生学号:");
                    removeStudent(scanner.nextInt());
                    break;
                }
                case 4:{
                    System.out.println("该功能尚未实现");
                    break;
                }
                case 5:{
                    List<Student> students = queryStudents();
                    students.forEach(System.out::println);
                    break;
                }
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入错误，请输入banner中显示的数字");
                    break;
            }
            System.out.println("ok!");
            System.out.println("按回车继续...");
            System.in.read();
            printBanner();
        }
    }

    /**
     * 打印控制信息，封装以备多次使用
     */
    private void printBanner(){
        System.out.println("***********************************\n" +
                "*                           1  插入                                  *\n" +
                "*                           2  查找                                  *\n" +
                "*                           3  删除                                  *\n" +
                "*                           4  修改(未实现）                          *\n" +
                "*                           5  输出                                  *\n" +
                "*                           6  退出                                  *\n" +
                "***********************************");
    }

    /**
     * 返回所有学生
     * @return all students
     */
    private List<Student> queryStudents(){
        if (stuList == null || stuList.size()==0){
            throw new RuntimeException("there is no student in studentsList");
        }
        return stuList;
    }

    /**
     * 删除指定学生
     * @param stuNo 学号
     * @return 成功返回true 失败(一般是学号不存在)返回false
     */
    private boolean removeStudent(Integer stuNo){
        Iterator<Student> iterator = stuList.iterator();
        while (iterator.hasNext()){
            Student next = iterator.next();
            if (next.getId().equals(stuNo)){
                stuList.remove(next);
                return true;
            }
        }
        return false;
    }

    /**
     * 新增学生
     * @param student 新增的学生信息
     * @return 成功返回true 失败(一般是学号重复或超过最大限制)返回false
     */
    private boolean insertStudent(Student student){
        if (stuList.size() >= 20){
            System.out.println("人数已满");
            return false;
        }
        for (Student stu : stuList) {
            if (stu.getId().equals(student.getId())){
                System.err.println(stu.getId()+" 已存在");
                return false;
            }
        }
        stuList.add(student);
        stuList.sort(Comparator.comparingInt(Student::getId));
        return true;
    }

    /**
     * 按名称查询
     * @param name 名字
     * @return 查询结果
     */
    private List<Student> queryStudentByName(String name){
        return stuList.stream().collect(ArrayList::new,(list, stu)->{
            if (stu.getName().equals(name)){
                list.add(stu);
            }
        },List::addAll);
    }

}
