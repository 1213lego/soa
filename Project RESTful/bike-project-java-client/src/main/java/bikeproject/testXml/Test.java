package bikeproject.testXml;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.transform.RegistryMatcher;
import org.simpleframework.xml.transform.Transform;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.*;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Test {

    private static final String URL = "http://localhost:8080/api/";
    private static final String URL_HEROKU = "https://fierce-wave-32830.herokuapp.com/api/";
    interface StudentService{
        @Headers({
                "Accept: application/xml"
        })
        @GET("student")
        Call<StudentList> getStudents();
        @Headers({
                "Accept: application/xml",
                "Content-Type: application/xml"
        })
        @POST("student")
        Call<Student> saveStudent(@Body Student student);
        @Headers({
                "Accept: application/xml"
        })
        @GET("student/{code}")
        Call<Student> findStudentByCode(@Path("code")String code);
    }
    static class DateFormatTransformer implements Transform<Date>
    {
        private DateFormat dateFormat;
        public DateFormatTransformer(DateFormat dateFormat) {
            this.dateFormat = dateFormat;
        }
        @Override
        public Date read(String value) throws Exception {
            return dateFormat.parse(value);
        }
        @Override
        public String write(Date value) throws Exception {
            return dateFormat.format(value);
        }

    }
    public static void main(String args[]) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        RegistryMatcher matcher = new RegistryMatcher();
        matcher.bind(Date.class,new DateFormatTransformer(dateFormat));
        Serializer serializer = new Persister(matcher);
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(URL)
                .addConverterFactory(SimpleXmlConverterFactory.create(serializer))
                .build();
        StudentService studentService = retrofit.create(StudentService.class);
        System.out.println(studentService.findStudentByCode("77777").execute().body());
        testList(studentService);
        testSave(studentService);
    }

    private static void testSave(StudentService studentService) throws IOException {
        System.out.println("Save ------------------------------------");
        Student student = new Student("77777","7777",new Date());
        Call<Student> c = studentService.saveStudent(student);
        Response<Student> response = c.execute();
        System.out.println(response.toString());
        System.out.println(response.headers().toString());
        if(response.isSuccessful()){
            System.out.println(response.body());
        }
    }

    public static void testList(StudentService studentService) throws IOException {
        System.out.println("List---------------------------------");
        Call<StudentList> call = studentService.getStudents();
        Response<StudentList> response = call.execute();
        System.out.println(response.toString());
        System.out.println(response.headers().toString());
        if(response.isSuccessful()){
            response.body().getStudentList().forEach((student -> System.out.println(student)));
        }
    }
}
