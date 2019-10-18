package bikeproject.testXml;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "List")
public class StudentList {
    @ElementList(inline=true)
    private List<Student> studentList;
    public StudentList(){}
    public List<Student> getStudentList() {
        return studentList;
    }
    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
