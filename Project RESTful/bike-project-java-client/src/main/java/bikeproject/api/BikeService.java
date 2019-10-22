package bikeproject.api;

import bikeproject.api.model.Bike;
import bikeproject.api.model.BikeList;
import bikeproject.api.model.BikeResponse;
import bikeproject.api.model.BikeErrorReponse;
import com.sun.deploy.util.StringUtils;
import okhttp3.Request;
import okio.Buffer;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import retrofit2.Call;
import retrofit2.Response;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BikeService {
    public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private ApiClient.BikeServiceRetrofit bikeServiceRetrofit;
    public BikeService(){
        bikeServiceRetrofit = ApiClient.getBikeService();
    }
    public List<Bike> getBikes() {
        List<Bike> bikeList = new ArrayList<>();
        try {
            Call<BikeList> bikeListCall = bikeServiceRetrofit.getBikes();
            Response<BikeList> bikeListResponse = bikeListCall.execute();
            if(bikeListResponse.isSuccessful()){
                bikeList = bikeListResponse.body().getBikeList();
            }
        }
        catch (Exception e){
            System.out.println("No hay conexion con el servidor: BikeService.getBikes()");
        }
        return bikeList;
    }
    public BikeResponse saveBike(Bike bike) throws Exception {
        BikeResponse bikeResponse = null;
        Call<BikeResponse> call = bikeServiceRetrofit.saveBike(bike);
        Response<BikeResponse> response = call.execute();
        if (response.isSuccessful()){
            bikeResponse = response.body();;
        }
        else {
            Serializer serializer = new Persister();
            BikeErrorReponse bikeErrorReponse = serializer.read(BikeErrorReponse.class,response.errorBody().byteStream());
            String errorMessages = StringUtils.join(bikeErrorReponse.getErrores(),"\n");
            throw new Exception(errorMessages);
        }
        return bikeResponse;
    }
    public Bike findBikeBySerial(String serial) throws Exception {
        Call<Bike> call = bikeServiceRetrofit.findBikeBySerial(serial);
        Response<Bike> response = call.execute();
        if(response.isSuccessful()){
            return response.body();
        }
        /*Serializer serializer = new Persister();
        BikeResponse bikeResponse = serializer.read(BikeResponse.class,response.errorBody().byteStream());
        throw new Exception(bikeResponse.getMessage());*/
        return null;
    }
    public BikeResponse deleteBikeBySerial(String serial) throws Exception {
        Call<BikeResponse> call = bikeServiceRetrofit.deleteBikeBySerial(serial);
        Response<BikeResponse> response = call.execute();
        if(response.isSuccessful()){
            return response.body();
        }
        Serializer serializer = new Persister();
        BikeResponse bikeResponse = serializer.read(BikeResponse.class,response.errorBody().byteStream());
        return bikeResponse;
    }
    private void testPrintBodyRequest(final Request request) throws IOException {
        final Request copy = request.newBuilder().build();
        final Buffer buffer = new Buffer();
        copy.body().writeTo(buffer);
        System.out.println("Body request");
        System.out.println(buffer.readUtf8());
    }
}
