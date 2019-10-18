package bikeproject.testJson;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class Test {

    private static final String URL = "http://localhost:8080/api/";
    private static final String URL_HEROKU = "https://fierce-wave-32830.herokuapp.com/api/";
    interface StudentService{
        @Headers({
                "Accept: application/json"
        })
        @GET("student")
        Call<List<Student>> getStudentsJson();
        @Headers({
                "Accept: application/json",
                "Content-Type: application/json"
        })
        @POST("student")
        Call<Student> saveStudentJsonBody(@Body Student student);
    }
    public static void main(String args[]) throws IOException {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd").create()))
                .build();
        StudentService studentService = retrofit.create(StudentService.class);
        testList(studentService);
        testSave(studentService);
    }

    private static void testSave(StudentService studentService) throws IOException {
        System.out.println("Save ------------------------------------");
        Student student = new Student("444444","44444444",new Date());
        Call<Student> c = studentService.saveStudentJsonBody(student);
        Response<Student> response = c.execute();
        System.out.println(response.toString());
        System.out.println(response.headers().toString());
        if(response.isSuccessful()){
            System.out.println(response.body());
        }
    }

    public static void testList(StudentService studentService) throws IOException {
        System.out.println("List---------------------------------");
        Call<List<Student>> call = studentService.getStudentsJson();
        Response<List<Student>> response = call.execute();
        System.out.println(response.toString());
        System.out.println(response.headers().toString());
        if(response.isSuccessful()){
            response.body().forEach((student -> System.out.println(student)));
        }
    }
}
