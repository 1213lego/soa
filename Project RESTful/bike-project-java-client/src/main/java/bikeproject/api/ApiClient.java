package bikeproject.api;

import bikeproject.api.model.BikeList;
import bikeproject.api.transform.DateFormatTransformer;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.transform.RegistryMatcher;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ApiClient {
    public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final String API_URL = "http://localhost:8080/api/";
    private static BikeServiceRetrofit bikeService;
    interface BikeServiceRetrofit {
        @Headers({
                "Accept: application/xml"
        })
        @GET("bike")
        Call<BikeList> getBikes();
    }
    public static BikeServiceRetrofit getBikeService(){
        if(bikeService==null){
            RegistryMatcher matcher = new RegistryMatcher();
            matcher.bind(Date.class,new DateFormatTransformer(dateFormat));
            Serializer serializer = new Persister(matcher);
            Retrofit retrofit = new Retrofit
                    .Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create(serializer))
                    .build();
            bikeService = retrofit.create(BikeServiceRetrofit.class);
        }
        return bikeService;
    }
}
