package bikeproject.testJson;
import com.google.gson.annotations.Expose;

import java.util.Date;
public class Student {
    private String code;
    private String name;
    private Date dateOfBirth;
    public Student(){}
    public Student(String code, String name,Date date) {
        this.code = code;
        this.name = name;
        this.dateOfBirth = date;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    @Override
    public String toString() {
        return "Student{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
