import java.util.Date;

public class Student implements Comparable<Student>{
    private Integer id;
    private String name;
    private Date birth;
    //true为男 false为女
    private boolean gender;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                ", gender=" + gender +
                '}';
    }

    @Override
    public int compareTo(Student s) {
        return s.getId() - this.getId();
    }
}
